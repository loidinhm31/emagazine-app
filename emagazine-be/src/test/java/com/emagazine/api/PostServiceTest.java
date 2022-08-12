package com.emagazine.api;

import com.emagazine.api.entity.Article;
import com.emagazine.api.entity.Post;
import com.emagazine.api.model.PostInstructionDTO;
import com.emagazine.api.repository.ArticleRepository;
import com.emagazine.api.repository.CommentRepository;
import com.emagazine.api.repository.PostRepository;
import com.emagazine.api.service.PostService;
import com.emagazine.api.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    private PostService postServiceTest;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private Clock clock;

    private static final ZonedDateTime NOW = ZonedDateTime.of(
            2022,
            1,
            15,
            12,
            30,
            30,
            0,
            ZoneId.of("GMT")
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Fix datetime for testing
        when(clock.getZone()).thenReturn(NOW.getZone());
        when(clock.instant()).thenReturn(NOW.toInstant());

        postServiceTest = new PostServiceImpl(
                clock,
                postRepository,
                articleRepository,
                commentRepository);
    }

    @ParameterizedTest
    @MethodSource("provideArgsForTestGetTopPost")
    void testTopPostOfNonRootArticles(List<Post> input,
                                      String fromGroupCheckOne, int expectedSizeOne, int expectedTotalCountOne,
                                      String fromGroupCheckTwo, int expectedSizeTwo, int expectedTotalCountTwo) {

        List<Long> returnArticleIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);

        when(articleRepository.findByTopNotRootAndView(10))
                .thenReturn(returnArticleIds);

        when(postRepository.findTop100ByArticleIds(returnArticleIds))
                .thenReturn(input);

        // Call method to test
        Map<String, List<PostInstructionDTO>> mapData =
                postServiceTest.findTopPostOfNonRootArticles();

        assertThat(mapData.get(fromGroupCheckOne)).hasSize(expectedSizeOne);
        assertThat(mapData.get(fromGroupCheckOne).stream()
                .mapToInt(PostInstructionDTO::getCountView)
                .sum()).isEqualTo(expectedTotalCountOne);

        assertThat(mapData.get(fromGroupCheckTwo)).hasSize(expectedSizeTwo);
        assertThat(mapData.get(fromGroupCheckTwo).stream()
                .mapToInt(PostInstructionDTO::getCountView)
                .sum()).isEqualTo(expectedTotalCountTwo);
    }

    private static Stream<Arguments> provideArgsForTestGetTopPost() {
        return Stream.of(
                Arguments.of(buildForTestMonthAndYear(), "A", 1, 1,"B", 1, 3),
                Arguments.of(buildForTestMonthYearAndRank(), "A", 2, 6, "B", 8, 40),
                Arguments.of(buildForTestZeroCount(), "A", 1, 0, "B", 1, 0)
        );
    }

    private static List<Post> buildForTestMonthAndYear() {
        // Test case 1:
        // (same month, same year) and (same month, diff year)
        // countView > 0
        // postMonth = todayMonth
        // articleRank = 6 (use article id 6)

        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String group;
            Date createdDate;
            int count;

            if (i == 1 || i == 2) {
                group = "A";
                if (i == 1) {                   // (same month, same year)
                    createdDate = Date.from(LocalDateTime.of(
                            2022, 1, 10, 10, 30)
                            .atZone(ZoneId.systemDefault()).toInstant());
                    count = 1;
                } else {                        // (same month, diff year)
                    createdDate = Date.from(LocalDateTime.of(
                                    2021, 1, 11, 12, 30)
                            .atZone(ZoneId.systemDefault()).toInstant());
                    count = 2;
                }
            } else {                            // (same month, same year)
                group = "B";
                createdDate = Date.from(LocalDateTime.of(
                                2022, 1, 12, 12, 30)
                        .atZone(ZoneId.systemDefault()).toInstant());
                count = 3;
            }
            postList.add(Post.builder()
                    .id((long) i)
                    .article(Article.builder()
                            .id(6L)
                            .name(group)
                            .build())
                    .countView(count)
                    .dateCreate(createdDate)
                    .build());
        }
        return postList;
    }

    private static List<Post> buildForTestMonthYearAndRank() {
        // Test case 2:                     // Test case: 3                     // Test case 4:                   // Test case 5
        // countView > 0                    // countView > 0                    // countView > 0                  // countView > 0
        // todayMonth - postMonth = 1       // todayMonth - postMonth = 1       // todayMonth - postMonth > 1     // todayMonth - postMonth > 1
        // i = 11, i = 12 (i < 20)          // i = 22 (postRank)                // i <= 8                         // i = 9, 10
        // articleRank = 6                  // articleRank = 6                  // articleRank = 5                // articleRank = 6

        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            Date createdDate;
            String group;
            long rank;
            int count;

            if (i == 11 || i == 12 || i == 22) {                   // Test case 2 (i = 11, 12),
                group = "A";                                       // Test case 3 (i = 22)
                rank = 6L;
                count = 3;
                createdDate = Date.from(LocalDateTime.of(
                                2021, 12, 15, 12, 30)
                        .atZone(ZoneId.systemDefault()).toInstant());

            } else {
                group = "B";
                createdDate = Date.from(LocalDateTime.of(
                        2021, 5, 15, 12, 30)
                        .atZone(ZoneId.systemDefault()).toInstant());
                if (i <= 8) {                                       // Test case 4
                    rank = 5L;
                    count = 5;
                } else {                                            // Test case 5
                    rank = 6L;
                    count = 7;
                }
            }
            postList.add(Post.builder()
                    .id((long) i)
                    .article(Article.builder()
                            .id(rank)
                            .name(group)
                            .build())
                    .countView(count)
                    .dateCreate(createdDate)
                    .build());
        }
        return postList;
    }

    private static List<Post> buildForTestZeroCount() {
        // Test case 6:                           // Test case 7:
        // countView = 0                          // countView = 0
        // articleRank = 3                        // articleRank = 4

        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Date createdDate = Date.from(LocalDateTime.of(
                            2022, 1, 15, 12, 30)
                    .atZone(ZoneId.systemDefault()).toInstant());
            String group;
            long rank;
            int count = 0;

            if (i == 1) {                               // Test case 5
                group = "A";
                rank = 3L;
            } else {
                group = "B";
                if (i == 2) {                           // Test case 5
                    rank = 3L;
                } else {                                // Test case 6
                    rank = 6L;
                }
            }
            postList.add(Post.builder()
                    .id((long) i)
                    .article(Article.builder()
                            .id(rank)
                            .name(group)
                            .build())
                    .countView(count)
                    .dateCreate(createdDate)
                    .build());
        }

        return postList;
    }
}

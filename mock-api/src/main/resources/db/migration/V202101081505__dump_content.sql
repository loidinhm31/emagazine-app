
UPDATE posts SET content = '
<div class="entry-content">
	<div style="margin-top: 20px">
		<p class="ads_displayed">Trong bài viết này chúng ta sẽ tìm hiểu
			về cách tìm số lớn nhất trong mảng trong Java. Đây là dạng bài tập
			thường gặp khi bắt đầu học một ngôn ngữ lập trình nào đó.</p>

		<p class="ads_displayed">
			Các bạn cần tìm hiểu về mảng, cách khởi tạo và in mảng trong Java
			trước đã nhé. Các bạn có thể tham thảo về <a
				href="https://freetuts.net/mang-mot-chieu-trong-java-1062.html"
				target="_blank" title="mang mot chieu trong java 1062 html">mảng
				một chiều trong Java</a>.
		</p>
		<ins class="adsbygoogle" style="display: block"
			data-ad-client="ca-pub-7868243767329230" data-ad-slot="4130622986"
			data-ad-format="auto" data-full-width-responsive="true"></ins>
		<script>
			(adsbygoogle = window.adsbygoogle || []).push({});
		</script>

		<div class="goto-wrapper mom_list">
			<p>Table of Content</p>
			<ul class="go-to-detail">
				<li class="list-goto-0"><a href="#goto-h2-0">Tìm số lớn
						nhất trong mảng Java đã khởi tạo sẵn</a></li>
				<li class="list-goto-1"><a href="#goto-h2-1">Tìm số lớn
						nhất trong mảng Java được nhập từ bàn phím.</a></li>
			</ul>
		</div>
		<h2 data-stt="0" id="goto-h2-0">Tìm số lớn nhất trong mảng Java
			đã khởi tạo sẵn</h2>

		<p class="ads_displayed">Đầu tiên mình sẽ khai báo một mảng
			numberArr[], sau đó khởi tạo các giá trị cho nó.</p>

		<div>
			<div>
				<div id="highlighter_714572" class="syntaxhighlighter  java">
					<div class="toolbar">
						<span><a href="#" class="toolbar_item command_help help">?</a></span>
					</div>
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="gutter"><div class="line number1 index0 alt2">1</div></td>
								<td class="code"><div class="container">
										<div class="line number1 index0 alt2">
											<code class="java keyword">double</code>
											<code class="java plain">[] numberArr = { </code>
											<code class="java value">12.3</code>
											<code class="java plain">, -</code>
											<code class="java value">4</code>
											<code class="java plain">, </code>
											<code class="java value">10</code>
											<code class="java plain">, </code>
											<code class="java value">4.24</code>
											<code class="java plain">, </code>
											<code class="java value">2</code>
											<code class="java plain">, </code>
											<code class="java value">71</code>
											<code class="java plain">, -</code>
											<code class="java value">2</code>
											<code class="java plain">, -</code>
											<code class="java value">6</code>
											<code class="java plain">};</code>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<p class="ads_displayed">Tiếp đến chúng ta cần một biến max làm
			biến trung gian được sử dụng để so sánh với các giá trị khác trong
			mảng. Max sẽ được gán bằng giá trị của phần tử đầu tiên trong mảng.</p>

		<p class="ads_displayed">Dùng một vòng lặp for để lặp tất cả các
			phần tử trong mảng và in nó ra màn hình.</p>

		<div>
			<div>
				<div id="highlighter_749136" class="syntaxhighlighter  java">
					<div class="toolbar">
						<span><a href="#" class="toolbar_item command_help help">?</a></span>
					</div>
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="gutter"><div class="line number1 index0 alt2">1</div>
									<div class="line number2 index1 alt1">2</div>
									<div class="line number3 index2 alt2">3</div>
									<div class="line number4 index3 alt1">4</div></td>
								<td class="code"><div class="container">
										<div class="line number1 index0 alt2">
											<code class="java plain">System.out.println(</code>
											<code class="java string">"\n\nCác phần tử trong mảng
												là: "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number2 index1 alt1">
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">double</code>
											<code class="java plain">i : numberArr){</code>
										</div>
										<div class="line number3 index2 alt2">
											<code class="java spaces">&nbsp;&nbsp;</code>
											<code class="java plain">System.out.print(i + </code>
											<code class="java string">", "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number4 index3 alt1">
											<code class="java plain">}</code>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<p class="ads_displayed">Cuối cùng ta cần thêm một vòng lặp for
			nữa để so sánh max với từng phần tử trong mảng, nếu số nào lớn hơn
			max thì gán nó cho max.</p>

		<div>
			<div>
				<div id="highlighter_735399" class="syntaxhighlighter  java">
					<div class="toolbar">
						<span><a href="#" class="toolbar_item command_help help">?</a></span>
					</div>
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="gutter"><div class="line number1 index0 alt2">1</div>
									<div class="line number2 index1 alt1">2</div>
									<div class="line number3 index2 alt2">3</div>
									<div class="line number4 index3 alt1">4</div></td>
								<td class="code"><div class="container">
										<div class="line number1 index0 alt2">
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">double</code>
											<code class="java plain">num: numberArr) {</code>
										</div>
										<div class="line number2 index1 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">if</code>
											<code class="java plain">(max &lt; num)</code>
										</div>
										<div class="line number3 index2 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">max = num;</code>
										</div>
										<div class="line number4 index3 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<p class="ads_displayed">
			<strong>Ví dụ:</strong> Mình sẽ viết một chương trình hoàn chỉnh để
			tìm số lớn nhất trong mảng đã khởi tạo giá trị sẵn.
		</p>

		<div>
			<div>
				<div id="highlighter_658914" class="syntaxhighlighter  java">
					<div class="toolbar">
						<span><a href="#" class="toolbar_item command_help help">?</a></span>
					</div>
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="gutter"><div class="line number1 index0 alt2">1</div>
									<div class="line number2 index1 alt1">2</div>
									<div class="line number3 index2 alt2">3</div>
									<div class="line number4 index3 alt1">4</div>
									<div class="line number5 index4 alt2">5</div>
									<div class="line number6 index5 alt1">6</div>
									<div class="line number7 index6 alt2">7</div>
									<div class="line number8 index7 alt1">8</div>
									<div class="line number9 index8 alt2">9</div>
									<div class="line number10 index9 alt1">10</div>
									<div class="line number11 index10 alt2">11</div>
									<div class="line number12 index11 alt1">12</div>
									<div class="line number13 index12 alt2">13</div>
									<div class="line number14 index13 alt1">14</div>
									<div class="line number15 index14 alt2">15</div>
									<div class="line number16 index15 alt1">16</div>
									<div class="line number17 index16 alt2">17</div>
									<div class="line number18 index17 alt1">18</div>
									<div class="line number19 index18 alt2">19</div>
									<div class="line number20 index19 alt1">20</div>
									<div class="line number21 index20 alt2">21</div>
									<div class="line number22 index21 alt1">22</div>
									<div class="line number23 index22 alt2">23</div>
									<div class="line number24 index23 alt1">24</div></td>
								<td class="code"><div class="container">
										<div class="line number1 index0 alt2">
											<code class="java keyword">import</code>
											<code class="java plain">java.util.Scanner;</code>
										</div>
										<div class="line number2 index1 alt1">
											<code class="java keyword">class</code>
											<code class="java plain">Main {</code>
										</div>
										<div class="line number3 index2 alt2">
											<code class="java spaces">&nbsp;&nbsp;</code>
											<code class="java keyword">public</code>
											<code class="java keyword">static</code>
											<code class="java keyword">void</code>
											<code class="java plain">main(String[] args) {</code>
										</div>
										<div class="line number4 index3 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//khai báo một mảng
												numberArr sau đó khởi tạo các giá trị cho các phần tử.</code>
										</div>
										<div class="line number5 index4 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">double</code>
											<code class="java plain">[] numberArr = { </code>
											<code class="java value">12.3</code>
											<code class="java plain">, -</code>
											<code class="java value">4</code>
											<code class="java plain">, </code>
											<code class="java value">10</code>
											<code class="java plain">, </code>
											<code class="java value">4.24</code>
											<code class="java plain">, </code>
											<code class="java value">2</code>
											<code class="java plain">, </code>
											<code class="java value">71</code>
											<code class="java plain">, -</code>
											<code class="java value">2</code>
											<code class="java plain">, -</code>
											<code class="java value">6</code>
											<code class="java plain">};</code>
										</div>
										<div class="line number6 index5 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//gán giá trị của phần tử
												đầu tiên cho max, sau đó dùng max so sánh với các phần tử
												còn lại trong mảng.</code>
										</div>
										<div class="line number7 index6 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">double</code>
											<code class="java plain">max = numberArr[</code>
											<code class="java value">0</code>
											<code class="java plain">];</code>
										</div>
										<div class="line number8 index7 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//in các phần tử trong
												mảng ra màn hình</code>
										</div>
										<div class="line number9 index8 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.println(</code>
											<code class="java string">"\n\nCác phần tử trong mảng
												là: "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number10 index9 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">double</code>
											<code class="java plain">i : numberArr){</code>
										</div>
										<div class="line number11 index10 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.print(i + </code>
											<code class="java string">", "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number12 index11 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
										<div class="line number13 index12 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//dùng vòng lặp for duyệt
												các phần tử trong mảng. so sánh max với từng phần tử nếu số
												nào lớn hơn max thì gán nó cho max.</code>
										</div>
										<div class="line number14 index13 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">// cứ lặp như vậy cho đến
												hết các phần tử thì max sẽ là giá trị lớn nhất trong mảng</code>
										</div>
										<div class="line number15 index14 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">double</code>
											<code class="java plain">num: numberArr) {</code>
										</div>
										<div class="line number16 index15 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">if</code>
											<code class="java plain">(max &lt; num)</code>
										</div>
										<div class="line number17 index16 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">max = num;</code>
										</div>
										<div class="line number18 index17 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
										<div class="line number19 index18 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.printf(</code>
											<code class="java string">"\nSố lớn nhất trong mảng
												là: %.2f"</code>
											<code class="java plain">, max);</code>
										</div>
										<div class="line number20 index19 alt1">&nbsp;</div>
										<div class="line number21 index20 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.println(</code>
											<code class="java string">"\n----------------------------------"</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number22 index21 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.println(</code>
											<code class="java string">"Chương trình này được đăng
												tại Freetuts.net"</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number23 index22 alt2">
											<code class="java spaces">&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
										<div class="line number24 index23 alt1">
											<code class="java plain">}</code>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<p class="ads_displayed">
			<strong>Kết quả:</strong>
		</p>
		<div id="netlink" class="hide-desktop">
			<div id="content-ad"
				style="overflow: hidden; position: relative; z-index: 2; width: 100%; height: 600px;">
				<div id="ad"
					style="position: fixed; z-index: 10000; top: 70px; display: none;">
					<ins class="adsbygoogle"
						style="display: inline-block; width: 300px; height: 600px;"
						data-ad-client="ca-pub-7868243767329230" data-ad-slot="3009113004"></ins>
				</div>
			</div>
		</div>
		<!-- detail_1 -->
		<ins class="adsbygoogle" style="display: block"
			data-ad-client="ca-pub-7868243767329230" data-ad-slot="8242625364"
			data-ad-format="auto" data-full-width-responsive="true"></ins>
		<script>
			(adsbygoogle = window.adsbygoogle || []).push({});
		</script>

		<p style="text-align: center;">
			<img alt="bai7 01 PNG"
				src="https://freetuts.net/upload/tut_post/images/2021/01/31/3543/bai7-01.PNG"
				style="width: 413px; height: 150px;">
		</p>

		<h2 data-stt="1" id="goto-h2-1">Tìm số lớn nhất trong mảng Java
			được nhập từ bàn phím.</h2>

		<p class="ads_displayed">Tương tư như ví dụ ở trên, nhưng trong
			phần này mình sẽ sử dụng class Scanner để lấy dữ liệu từ bàn phím.
			Nhớ import thư viện ở đầu file trước đã nhé:</p>

		<div>
			<div>
				<div id="highlighter_694156" class="syntaxhighlighter  java">
					<div class="toolbar">
						<span><a href="#" class="toolbar_item command_help help">?</a></span>
					</div>
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="gutter"><div class="line number1 index0 alt2">1</div></td>
								<td class="code"><div class="container">
										<div class="line number1 index0 alt2">
											<code class="java keyword">import</code>
											<code class="java plain">java.util.Scanner;</code>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<p class="ads_displayed">Chúng ta cần một biến n là số lượng các
			phần tử trong mảng, n được nhập từ bàn phím.</p>

		<p class="ads_displayed">Tiếp đến cần một vòng lặp for lặp từ 0
			đến n để nhập giá trị cho các phần tử trong mảng.</p>

		<div>
			<div>
				<div id="highlighter_874102" class="syntaxhighlighter  java">
					<div class="toolbar">
						<span><a href="#" class="toolbar_item command_help help">?</a></span>
					</div>
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="gutter"><div class="line number1 index0 alt2">1</div>
									<div class="line number2 index1 alt1">2</div>
									<div class="line number3 index2 alt2">3</div>
									<div class="line number4 index3 alt1">4</div>
									<div class="line number5 index4 alt2">5</div></td>
								<td class="code"><div class="container">
										<div class="line number1 index0 alt2">
											<code class="java comments">//nhập giá trị cho từng
												phần tử trong mảng</code>
										</div>
										<div class="line number2 index1 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">int</code>
											<code class="java plain">i = </code>
											<code class="java value">0</code>
											<code class="java plain">; i &lt; n; i++) {</code>
										</div>
										<div class="line number3 index2 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.print(</code>
											<code class="java string">"\nNhập phần tử thứ "</code>
											<code class="java plain">+ i + </code>
											<code class="java string">": "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number4 index3 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">numberArr[i] = sc.nextInt();</code>
										</div>
										<div class="line number5 index4 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<p class="ads_displayed">Như vậy là ta đã có dữ liệu để thực hiện
			tìm số lớn nhất, bây giờ thực hiện như ví dụ 1 là có thể tìm được số
			lớn nhất trong mảng rồi đấy.</p>

		<p class="ads_displayed">
			<strong>Ví dụ: </strong>Mình sẽ viết hoàn chỉnh chương trình tìm số
			lớn nhất trong mảng được nhập từ bàn phím.
		</p>

		<div>
			<div>
				<div id="highlighter_702554" class="syntaxhighlighter  java">
					<div class="toolbar">
						<span><a href="#" class="toolbar_item command_help help">?</a></span>
					</div>
					<table border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="gutter"><div class="line number1 index0 alt2">1</div>
									<div class="line number2 index1 alt1">2</div>
									<div class="line number3 index2 alt2">3</div>
									<div class="line number4 index3 alt1">4</div>
									<div class="line number5 index4 alt2">5</div>
									<div class="line number6 index5 alt1">6</div>
									<div class="line number7 index6 alt2">7</div>
									<div class="line number8 index7 alt1">8</div>
									<div class="line number9 index8 alt2">9</div>
									<div class="line number10 index9 alt1">10</div>
									<div class="line number11 index10 alt2">11</div>
									<div class="line number12 index11 alt1">12</div>
									<div class="line number13 index12 alt2">13</div>
									<div class="line number14 index13 alt1">14</div>
									<div class="line number15 index14 alt2">15</div>
									<div class="line number16 index15 alt1">16</div>
									<div class="line number17 index16 alt2">17</div>
									<div class="line number18 index17 alt1">18</div>
									<div class="line number19 index18 alt2">19</div>
									<div class="line number20 index19 alt1">20</div>
									<div class="line number21 index20 alt2">21</div>
									<div class="line number22 index21 alt1">22</div>
									<div class="line number23 index22 alt2">23</div>
									<div class="line number24 index23 alt1">24</div>
									<div class="line number25 index24 alt2">25</div>
									<div class="line number26 index25 alt1">26</div>
									<div class="line number27 index26 alt2">27</div>
									<div class="line number28 index27 alt1">28</div>
									<div class="line number29 index28 alt2">29</div>
									<div class="line number30 index29 alt1">30</div>
									<div class="line number31 index30 alt2">31</div>
									<div class="line number32 index31 alt1">32</div>
									<div class="line number33 index32 alt2">33</div></td>
								<td class="code"><div class="container">
										<div class="line number1 index0 alt2">
											<code class="java keyword">import</code>
											<code class="java plain">java.util.Scanner;</code>
										</div>
										<div class="line number2 index1 alt1">
											<code class="java keyword">class</code>
											<code class="java plain">Main {</code>
										</div>
										<div class="line number3 index2 alt2">
											<code class="java spaces">&nbsp;&nbsp;</code>
											<code class="java keyword">public</code>
											<code class="java keyword">static</code>
											<code class="java keyword">void</code>
											<code class="java plain">main(String[] args) {</code>
										</div>
										<div class="line number4 index3 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//sử dụng class Scanner để
												lấy dữ liệu từ bàn phím</code>
										</div>
										<div class="line number5 index4 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">Scanner sc = </code>
											<code class="java keyword">new</code>
											<code class="java plain">Scanner(System.in);</code>
										</div>
										<div class="line number6 index5 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//khai báo biến n là số
												lượng phần tử trong mảng</code>
										</div>
										<div class="line number7 index6 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">int</code>
											<code class="java plain">n;</code>
										</div>
										<div class="line number8 index7 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.print(</code>
											<code class="java string">"\n\nNhập số lượng phần tử
												cho mảng: "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number9 index8 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">n = sc.nextInt();</code>
										</div>
										<div class="line number10 index9 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">int</code>
											<code class="java plain">numberArr[] = </code>
											<code class="java keyword">new</code>
											<code class="java keyword">int</code>
											<code class="java plain">[n];</code>
										</div>
										<div class="line number11 index10 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//nhập giá trị cho từng
												phần tử trong mảng</code>
										</div>
										<div class="line number12 index11 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">int</code>
											<code class="java plain">i = </code>
											<code class="java value">0</code>
											<code class="java plain">; i &lt; n; i++) {</code>
										</div>
										<div class="line number13 index12 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.print(</code>
											<code class="java string">"\nNhập phần tử thứ "</code>
											<code class="java plain">+ i + </code>
											<code class="java string">": "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number14 index13 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">numberArr[i] = sc.nextInt();</code>
										</div>
										<div class="line number15 index14 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
										<div class="line number16 index15 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//in các phần tử trong
												mảng ra màn hình</code>
										</div>
										<div class="line number17 index16 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.println(</code>
											<code class="java string">"Các phần tử trong mảng là:
												"</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number18 index17 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">double</code>
											<code class="java plain">i : numberArr){</code>
										</div>
										<div class="line number19 index18 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.print(i + </code>
											<code class="java string">", "</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number20 index19 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
										<div class="line number21 index20 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">double</code>
											<code class="java plain">max = numberArr[</code>
											<code class="java value">0</code>
											<code class="java plain">];</code>
										</div>
										<div class="line number22 index21 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">//dùng vòng lặp for duyệt
												các phần tử trong mảng. so sánh max với từng phần tử nếu số
												nào lớn hơn max thì gán nó cho max.</code>
										</div>
										<div class="line number23 index22 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java comments">// cứ lặp như vậy cho đến
												hết các phần tử thì max sẽ là giá trị lớn nhất trong mảng</code>
										</div>
										<div class="line number24 index23 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">for</code>
											<code class="java plain">(</code>
											<code class="java keyword">double</code>
											<code class="java plain">num: numberArr) {</code>
										</div>
										<div class="line number25 index24 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java keyword">if</code>
											<code class="java plain">(max &lt; num)</code>
										</div>
										<div class="line number26 index25 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">max = num;</code>
										</div>
										<div class="line number27 index26 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
										<div class="line number28 index27 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.printf(</code>
											<code class="java string">"\nSố lớn nhất trong mảng
												là: %.2f"</code>
											<code class="java plain">, max);</code>
										</div>
										<div class="line number29 index28 alt2">&nbsp;</div>
										<div class="line number30 index29 alt1">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.println(</code>
											<code class="java string">"\n----------------------------------"</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number31 index30 alt2">
											<code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>
											<code class="java plain">System.out.println(</code>
											<code class="java string">"Chương trình này được đăng
												tại Freetuts.net"</code>
											<code class="java plain">);</code>
										</div>
										<div class="line number32 index31 alt1">
											<code class="java spaces">&nbsp;&nbsp;</code>
											<code class="java plain">}</code>
										</div>
										<div class="line number33 index32 alt2">
											<code class="java plain">}</code>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<p class="ads_displayed">
			<strong>Kết quả:</strong>
		</p>
		<!-- detail_2 -->
		<ins class="adsbygoogle" style="display: block"
			data-ad-client="ca-pub-7868243767329230" data-ad-slot="5224406327"
			data-ad-format="auto" data-full-width-responsive="true"></ins>
		<script>
                                         (adsbygoogle = window.adsbygoogle || []).push({});
                                    </script>

		<p style="text-align: center;">
			<img alt="bai7 02 PNG"
				src="https://freetuts.net/upload/tut_post/images/2021/01/31/3543/bai7-02.PNG">
		</p>

		<p class="end-counter">
			Như vậy là chúng ta đã tìm hiểu xong cách tìm kiếm số lớn nhất trong
			mảng trong Java. Các bạn có thể tham khảo các bài tập Java khác tại <a
				href="https://freetuts.net/hoc-java/bai-tap-java" target="_blank"
				title="bai tap java">Bài tập Java</a>, Chúc các bạn thành công !!!
		</p>
		<script type="text/javascript"
			src="https://freetuts.net/public/syntaxhighlighter/scripts/shCore.js"></script>
		<link type="text/css" rel="stylesheet"
			href="https://freetuts.net/public/syntaxhighlighter/styles/shCoreDefault.css">
		<script type="text/javascript"
			src="https://freetuts.net/public/syntaxhighlighter/scripts/shBrushJava.js"></script>
		<script type="text/javascript"> SyntaxHighlighter.config.stripBrs = false; /*SyntaxHighlighter.config.tagName = "pre";*/ SyntaxHighlighter.defaults["gutter"] = true; SyntaxHighlighter.all();</script>
		<!-- detail_bottom -->
		<ins class="adsbygoogle" style="display: block"
			data-ad-client="ca-pub-7868243767329230" data-ad-slot="3771530339"
			data-ad-format="auto" data-full-width-responsive="true"></ins>
		<script>
                                 (adsbygoogle = window.adsbygoogle || []).push({});
                            </script>



		<div style="overflow: hidden">

			<span class="mom_button_wrap"><a
				style="float: left; background: #ddd !important; cursor: text"
				onclick="return false"
				class="call-to-action button mom_button red_bt" href="#"
				rel="nofollow" target="_blank" title=" "><i
					class="momizat-icon-previous"></i>Bài sau</a></span> <span
				class="mom_button_wrap"><a style="float: right"
				class="call-to-action button mom_button red_bt"
				href="https://freetuts.net/bai-tap-java-oop-chuong-trinh-nhap-xuat-thong-tin-sinh-vien-2739.html"
				title="Bài tập Java OOP: Chương trình nhập xuất thông tin sinh viên">Bài
					tiếp<i class="momizat-icon-next"></i>
			</a></span>
		</div>


	</div>
</div>'
WHERE id = 6;
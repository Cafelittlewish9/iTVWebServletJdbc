<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LiveStram and Videos</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" media="screen"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link href="css/SearchPageBorder.css" rel="stylesheet">

<!-- searchbar所需的 -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/searchbar.css" />

<!-- 檔案上傳所需的 -->

<!-- LiveStram and Videos所需的 -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/LiveStream-Videos.css" />
<!-- Chang URLs to wherever Video.js files will be hosted -->
<!-- video.js must be in the <head> for older IEs to work. -->

<!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
<script>
	videojs.options.flash.swf = "video-js.swf";
</script>
<script type="text/javascript" src="js/temp.js"></script>
</head>
<body style="background: rgba(0, 0, 0, 0.05)">
	<!-- Content -->
	<section class="content">
		<!-- Page header -->
		<section class="browse-header"></section>
		<!-- End Page header -->
		<!-- Page wrapper -->
		<div class="browse-wrapper">
			<!-- Main part of page -->
			<section class="browse-main">
				<div class="browse-main-header-updated">
					<ul class="browse-main-nav nav nav-tabs clearfix">
						<li><a href="LiveStream.jsp">Livestreams</a></li>
						<li class="active"><a href="#">Videos</a></li>
					</ul>
				</div>
				<div class="tab-content">
					<div class="tab-pane active" id="livestreams">
						<div class="browse-main-items">
							<div id="livestream_items" class="browse-main-videos clearfix">
								<table>
									<tr>
										<div class="browse-main-filters">
											<button type="button" value="" id="browse_type_any"
												class="browse-main-filters--button js-browse-product-type active">All</button>
											<button type="button" value="MusicVideo"
												id="browse_type_MusicVideo"
												class="browse-main-filters--button js-browse-product-type ">MusicVideo</button>
											<button type="button" value="News" id="browse_type_News"
												class="browse-main-filters--button js-browse-product-type ">News</button>
											<button type="button" value="Drama" id="browse_type_Drama"
												class="browse-main-filters--button js-browse-product-type ">Drama</button>
											<button type="button" value="Animation"
												id="browse_type_Animation"
												class="browse-main-filters--button js-browse-product-type ">Animation</button>
											<button type="button" value="Daily" id="browse_type_Daily"
												class="browse-main-filters--button js-browse-product-type ">Daily</button>
											<button type="button" value="Interest"
												id="browse_type_Interest"
												class="browse-main-filters--button js-browse-product-type ">Interest</button>
											<button type="button" value="Other" id="browse_type_Other"
												class="browse-main-filters--button js-browse-product-type ">Other</button>
										</div>
										<div id="videos_div1"></div>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</section>
</body>
</html>
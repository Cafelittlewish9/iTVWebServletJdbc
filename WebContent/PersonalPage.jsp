<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.* , java.lang.Math"%>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PersonalPage</title>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
	<link href="css/SearchPageBorder.css" rel="stylesheet">
	
	<!-- searchbar所需的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="css/searchbar.css" />
	
	<!-- 檔案上傳所需的 -->
	<script src="js/bootstrap.file-input.js"></script>
	<script src="js/js.cookie.js"></script>
	
	<link href="css/PersonalPage.css" rel="stylesheet">

<script type="text/javascript">
var divs = document.getElementById("div1");


$(function(){
		$.ajax({
			url:'VideoServlet',
			type:'get',
// 			data:{'categoryID':1},
			dataType:"json",
			success:function(data){
				$.each(data,function(i,v){
					//順序=最新-舊
					$('#tr1').prepend("<td style='margin:20px 20px 20px 30px ; float:left ;  list-style-type:none;' >")
					$('#tr1 td:first').append("<a href='PlayVideo.jsp?filename="+v.videoName + "'><img src='../img/"+v.videoName +".jpg'/></a><br>");
					$('#tr1 td:first').append("<a style='text-decoration:none;color:#167ac6;font-weight: 600;font-family:sans-serif; font-size:15px' href='PlayVideo.jsp?filename="+v.videoName+"'><span>"+v.videoName+"</span></a><br>");
					$('#tr1 td:first').append("<span style='font-weight: 550;font-family:Microsoft JhengHei; font-size:12px'>"+v.videoWatchTimes+ "views</span><br></td>");
					
				});
			}
		})
	})
	
</script>


</head>

<body style="background:rgba(0,0,0,0.05);font-family:Microsoft JhengHei">

	 <jsp:include page="/Header.jsp" />
   
   	<jsp:include page="/Bar.jsp" />
   	

	
	
<div class="feed-page">
		
						<div class="latest-news-header">
								<h2 class="latest-news-header--title">Yosuke</h2>
								<div class="latest-news-header--date">Monday, September 14, 2015</div>
						</div>	
	
	<div class="feed-page--main">
		<div class="feed-page--left">
			<article class="feed-card feed-card__promoted feed-card__promoted-landscape ">
			
				<div class="feed-card--image">
					<a  href="">
						<img src="img/test.jpg" class="picture-img"/>
					</a>
				</div>
				
				<div class="feed-card--meta">
					<time class="feed-card--time">39 minutes ago</time>
				
					
					<button type="button" class="feed-card--category" id="video_setting" data-toggle="modal" data-target="">
							Setting
					</button>
					
				</div>
				
				<div class="feed-card--info">
					<h2 class="feed-card--title">
						<a href="">Everything That Happened on Day Four of New York Fashion Week</a>
					</h2>
		
					<div class="feed-card--byline" style="float:left"><span>by Steff Yotka</span> 
							
					</div>
				</div>
			</article> 
			<hr style="border:0; height:1px; background-color:#d4d4d4;color:#d4d4d4">
			</div>
			
			
			<div class="feed-page--left">
			<article class="feed-card feed-card__promoted feed-card__promoted-landscape ">
				<div class="feed-card--image">
					<a  href="">
						<img src="img/test.jpg" class="picture-img"/>
					</a>
				</div>
				
				<div class="feed-card--meta">
					<time class="feed-card--time">39 minutes ago</time>
				
					<h2 class="feed-card--category"><a href="http://www.vogue.com/fashion-shows/">Runway</a></h2>
				</div>
				
				<div class="feed-card--info">
					<h2 class="feed-card--title">
						<a href="">Everything That Happened on Day Four of New York Fashion Week</a>
					</h2>
		
					<div class="feed-card--byline"><span>by</span> Steff Yotka </div>
							
				</div>
			</article> 
			<hr style="border:0; height:1px; background-color:#d4d4d4;color:#d4d4d4">
			
			</div>
	  </div>	
	  
	<!-- 	  追蹤列表  -->
	  <div class="row">
		 <div class="col-md-4" style="background-color:white;width: 300px;margin-top:30px">
		          <div class="stream-chat theater-mode-chat-container">
		            
		              <div class="placeholder-for-chat no-chat">
		              
		                <div class="friendslist">
		                
			                <div class="friends">
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4> KKSS</h4></div>
						     
						     <div class="friends" >
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4> KKSS</h4></div>
				            
				            <div class="friends">
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4> KKSS</h4></div>
				            
				            <div class="friends">
				                <img src="img/photo.png" />
				            </div >
				            <div style="height: 38px;"><h4>KKSS</h4></div>
		                  
		                </div>
		              </div>
		            
		          </div>
		  </div>
	  </div>
	  
</div>
	

<jsp:include page="/Footer.jsp" />

</body>
</html>
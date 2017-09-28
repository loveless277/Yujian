$(function(){
    hdshow();
	$(window).on('scroll',hdshow);
	
	//点击弹出侧导航栏
	$('.header-bottom .bbox-left').click(show_side)
	//隐藏导航栏
	$('body').on("click",'.nav_side_bg',hide_side)
	$(".side_header>button").click(hide_side)
	
	// 滚动加载
	$(window).on('scroll',scrollLoad);
})

//头部效果
function hdshow(){
	if(window.scrollY>=70){
		$('body').removeClass("htvisible")
	} else {
		$('body').addClass("htvisible")
	}
}

//弹出侧边栏
function show_side(){
	$('.nav_side').addClass('go_right')
	$('.main').addClass('go_right')
	// 显示帘幕
	$('.nav_side_bg').show(0).removeClass('opacity_0')
	// 回到顶部
	$(window).scrollTop(0);
	hdshow();
	$('body').addClass('height100');
}

//隐藏侧边栏
function hide_side(){
	$('.nav_side').removeClass('go_right')
	$('.main').removeClass('go_right')
	// 取消帘幕
	$('.nav_side_bg').addClass('opacity_0')
	setTimeout(function(){
		$('.nav_side_bg').hide(0)
	},500)
	$('body').removeClass('height100');
}

// 滚动加载
function scrollLoad(){
	var $body_height = $("html").height();
	var $scroll_top = $(document).scrollTop();
	var $doc_innerHeight = $(window).height();
	if($body_height-$scroll_top-$doc_innerHeight == 0){   			

//      var result = [{}];
//		$.each(result, function(i, item) {
//          	
//          		var img="<img src='"+item.manhua_img+"' srcset='"+item.manhua_img+" 2x'></img>";
//          		var divi="<div><i class='glyphicon glyphicon-share-alt'></i></div>";
//          		var a="<a href='#'>"+img+divi+"</a>";
//          		var itemimg="<div class='itemimg'>"+a+"</div>";
//
//
//          		var h4="<h4>"+item.manhua_title+"</h4>";
//          		var message="<div>"+h4+"</div>";
//          		var boxinfo="<div>"+message+"</div>";
//
//          		var boxitem="<div class='boxitem'>"+itemimg+boxinfo+"</div>";
//          		var li="<li class='col-lg-4 col-md-4 col-sm-6 col-xs-12'>"+boxitem+"</li>";
//
//          		$(".listitems").append(li);
//     			})

		$.ajax({
			type : 'get',
			url : './data/data.json',
			dataType: 'json',
			async: false
		}).done((back)=>{
			if(back == "") return;
			console.log(back);
			var html = '';
			back.forEach(function(item,i){
				html = `<li class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<div class="boxitem">
								<div class="itemimg">
									<a href="#">
										<img src="${item.manhua_img}" srcset="${item.manhua_img} 2x" />
										<div>
											<i class="glyphicon glyphicon-share-alt"></i>
										</div>
									</a>
								</div>
								<div class="boxinfo">
									<div class="message">
										<h4>${item.manhua_title}</h4>
									</div>
								</div>
							</div>
						</li>`;
				$(".listitems").append(html);
			})
		})

	}
}
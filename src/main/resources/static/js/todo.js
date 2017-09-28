/**
 * Created by acerpc on 2017/8/22.
 */
// $(document).ready(function () {
//
// })

$(function(){


     //定义
     var li_actnum = 0;
     var $toggleall =$("#toggle-all");
     var $ul = $("#todo-list");
     var word = 'items left';

    //界面加载完毕后获取内容
    $.ajax({
        url : "/todos",
        type : "get"
    }).done(function(res){
        //获取数据个数并显示
        li_actnum = res.result.length;
        show_li_actnum();
        // 生成html
        if(li_actnum != 0) {
            res.result.forEach(function (item, i) {
                var $li = `<li data-id="${item.id}">
                 <div class='view'>
                 <input class='toggle' type='checkbox'>
                 <label >${item.title}</label>
                 <button class='destroy'>
                 </button>
                 </div>
                 <input class='edit' value="">
                 </li>`;
                $ul.append($li);
                if (item.completed == 1) {
                    $("li[data-id=" + item.id+"]").find(".toggle").click();
                }
            })
        }
        select_all_checked();
        show_footer();
        show_btncompleted();
    })

    //显示未完成事项数量
    function show_li_actnum() {
        $("#todo-count").find("strong").text(li_actnum);
        if (li_actnum>1) {
            $("#todo-count").find("span").text(word);
        }else {
            $("#todo-count").find("span").text("item left");
        }
    }

    //判定是否显示副本
    function show_footer() {
        if($("#todo-list>li").length==0){
            //全选框
            $("#toggle-all").hide();
            //footer
            $("#footer").hide();
        }else{
            $("#toggle-all").show();
            $("#footer").show();
        }
    }

    //判断是否显示清除所有键
    function show_btncompleted() {
        if($(".completed").length==0){
            $("#clear-completed").hide();
        }else {
            $("#clear-completed").show();
        }
    }

    //判断是否有未完成任务，如果有全选按钮默认不选中
    function select_all_checked() {
        if($("li.completed").length==$("#todo-list>li").length){
            $toggleall.prop("checked",true);
        }else {
            $toggleall.prop("checked",false);
        }
    }

    //footer选中时加class
    function footer_li_selected(el){
        $("#filters").find("li>a").removeClass('selected');
        $(el).addClass('selected');
    }

    //添加事项
     $('#new-todo').keydown(function (e) {
         var $input = $('#new-todo').val();
         if (e.keyCode==13 && $input!="") {

             // $ul.append("<li>" +
             //     "<div class='view'>" +
             //     "<input class='toggle' type='checkbox'>" +
             //     "<label >" +
             //     $input +
             //     "</label>" +
             //     "<button class='destroy'>" +
             //     "</button>" +
             //     "</div>" +
             //     "<input class='edit' value='"+$input+"'>" +
             //     "</li>");

             $.ajax({
                 url : "/todos",
                 type : "post",
                 contentType: "application/json;charset=UTF-8",
                 data:JSON.stringify({
                     "title":$input
                 })
             }).done((res)=>{
                if(res.code!='200') return;
                 var html = `<li data-id="${res.result[0].id}">
                 <div class='view'>
                 <input class='toggle' type='checkbox'>
                 <label >${$input}</label>
                 <button class='destroy'>
                 </button>
                 </div>   
                 <input class='edit' value="">
                 </li>`;
                 $ul.append(html);
                 $(this).val("");
                 //增加数量
                 li_actnum++;
                 show_li_actnum();
                 //判定是否显示footer
                 show_footer();
                 show_btncompleted();
                select_all_checked();
                 $(".selected").click();
             })
         }
     })

    //判断总checkbox
    $toggleall.click(function () {
        var $toggle = $(".toggle");
        var $completed = 0;
        if($(this).is(":checked")){
            $completed = 1;
        }
        $.ajax({
            url : "/todos/allcom",
            type : "put",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                "completed": $completed
            })
        }).done((res)=>{
            if(res.code == '200'){
                if($(this).is(":checked")){
                    $toggle.prop("checked",true);
                    $("li").addClass("completed");
                    //减少数量
                    li_actnum=0;

                }else {
                    $toggle.prop("checked",false);
                    $("li").removeClass("completed");
                    li_actnum = $("#todo-list>li").length - $("#todo-list .completed").length;
                }
                show_li_actnum();
                show_btncompleted();
                $(".selected").click();
            }
        })
    })

    //判断单个checkbox

    $ul.on('click','.toggle',function () {
        var $this_li = $(this).parent().parent();
        var $data_id = $this_li.data("id");
        var $completed = 0;
        if($(this).is(":checked")){
            $completed = 1;
        }
        $.ajax({
            url : "/todos/"+$data_id+"/com",
            type : "put",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                "completed": $completed
            })
        }).done((res)=>{
            if(res.code == '200'){
                if($(this).is(":checked")){
                    $this_li.addClass("completed");
                    //减少数量
                    li_actnum--;
                }else {
                    $this_li.removeClass("completed");
                    //增加数量
                    li_actnum++;
                }
                select_all_checked();
                show_li_actnum();
                show_btncompleted();
                $(".selected").click();
            }
        })
    })

    //编辑事项
    $ul.on('dblclick','label',function () {
       var $this_li = $(this).parent().parent();
        $this_li.addClass("editing");
        //获取label文本内容
        $value = $(this).text();
        //设置编辑框的值
        $this_li.find(".edit").val($value);
        $this_li.find(".edit").focus();
   })

    //取消编辑事项并保存
    function noEdit(el) {
        var $this_li = $(el).parent();
        var $value = $this_li.find(".edit").val();
        if($value==""){
            $this_li.find(".destroy").click();
            return;
        }
        var $data_id = $this_li.data("id");

        $.ajax({
            url : "/todos/"+$data_id,
            type : "put",
            contentType: "application/json;charset=UTF-8",
            data:JSON.stringify({
                "title": $value
            })
        }).done((res)=>{
            if(res.code == '200'){
                //设置label的文本为编辑框的值
                $this_li.find("label").text( $value);
                $this_li.removeClass("editing");
            }
        })
    }
    //失去焦点
    $ul.on('blur','.edit',function () {
        noEdit(this);
    });
     //回车
    $ul.on('keydown','.edit',function(e){
        if(e.keyCode == 13){
            noEdit(this);
        }
    });

    //删除事项
    $ul.on('click','.destroy',function () {
        var $this_li = $(this).parent().parent();
        var $data_id = $this_li.data("id");
        if(!confirm("你确定要删除该事项吗？")) return;
        $.ajax({
            url : "/todos/"+$data_id,
            type : "delete",
            contentType: "application/json;charset=UTF-8"
        }).done((res)=>{
            if(res.code == '200'){
                $this_li.remove();
                if(!$this_li.hasClass("completed")){
                    //减少数量
                    li_actnum--;
                }

                show_li_actnum();
                //判定是否显示footer
                show_footer();
                show_btncompleted();
            }
        })
    })

    //清除所有已完成事项
    $("#footer").on('click','#clear-completed',function () {
        if(!confirm("你确定要删除所有已完成事项吗？")) return;
        $.ajax({
            url : "/todos/deletecom",
            type : "delete",
            contentType: "application/json;charset=UTF-8"
        }).done((res)=>{
            if(res.code == '200'){
                $("#todo-list .completed").remove();
                $toggleall.prop("checked",false);
                show_btncompleted();
                show_li_actnum();
                //判定是否显示footer
                show_footer();
            }
        });
    })

    //显示未完成事项
    $("#active").click(function(){
        footer_li_selected(this);
        $("#todo-list>li").show();
        $("#todo-list .completed").hide();
    })
    //显示完成事项
    $("#completed").click(function(){
        footer_li_selected(this);
        $("#todo-list>li").hide();
        $("#todo-list .completed").show();
    })
    //显示所有事项
    $("#all").click(function () {
        footer_li_selected(this);
        $("#todo-list>li").show();
    })

})
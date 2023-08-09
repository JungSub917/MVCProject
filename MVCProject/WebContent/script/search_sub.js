// 서브 이미지 => 메인 이미지 교체
function dispcopy(i) {
  document.getElementById("main_img").src = i;
}

$(function(){
  $('.nat_tour_price').each(function() {
    let natourPrice = $(this).text();
    natourPrice = Number(natourPrice);
    natourPrice = natourPrice.toLocaleString();
    natourPrice = natourPrice + "원";
    $(this).text(natourPrice);
  });
});
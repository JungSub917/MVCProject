<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>상상속 유럽여행을 현실로, 스투비 플래너</title>
  <link rel="icon" href="images/favicon.png">
  <link rel="stylesheet" href="css/reset.css">
  <link rel="stylesheet" href="css/login.css">
  <script src="script/jquery-3.7.0.js"></script>
  <!-- 네이버 로그인  -->
  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  <!-- 카카오 로그인 -->
	<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.2.0/kakao.min.js"
  integrity="sha384-x+WG2i7pOR+oWb6O5GV5f1KN2Ko6N7PTGPS7UlasYWNxZMKQA63Cj/B2lbUmUfuC" crossorigin="anonymous"></script>
</head>
<body>
<%= request.getAttribute("LoginErrMsg") == null ? "" : request.getAttribute("LoginErrMsg") %>
  <div class="background_video">
    <video id="background_video" muted autoplay loop>
      <source src="" type="videos/mp4">
    </video>
  </div>
  <section>
    <div class="login_box">
      <div class="login_contain">
        <div class="login_content">
          <a href="Controller?command=MainPage"><img src="images/stu_logo.png" alt=""></a>
          <form name="frm" action="Controller" method="get">
          	<input type="hidden" name="command" value="LoginAction">
            <div class="email" >
              <label for="text">이메일</label>
              <input type="text" id="id" name="id" autocomplete="off">
            </div>
            <div class="pw">
              <label for="password">비밀번호</label>
              <input type="password" id="password" name="pw" autocomplete="off">
            </div>
            <div class="forget_pass">
              <a href="Findpw.jsp">비밀번호를 잊으셨나요?</a>
            </div>
            <div class="login_btn">
              <button type="submit" id="log_btn">로그인</button>
            </div>
          </form>
          <div class="signup">
            <p>회원이 아니세요? <span><a href="Signup.jsp">회원가입히기</a></span></p>
          </div>
          <div class="divider-container">
            <div class="divider"></div>
            <span>or</span>
          </div>
          <div class="social_login">
            <p>SNS 간편 로그인</p>
            <div class="social_btn">
              <div class="kakao"><a href="#" onclick="kakaoLogin();" ><img src="images/Login/kakaolink_btn.png" alt=""></a></div>
              <div class="naver"><a id="naver_id_login" href="#"><img src="images/Login/naver_btn.png" alt=""></a></div>
              <div class="google g-signin2" data-onsuccess="onSignLn"><a href="#"><img src="images/Login/google_btn.png" alt=""></a></div>
              <div style="clear:both"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <script>
    // 로그인 랜덤 비디오 재생
    $(function(){
      // src에 직접적으로 대입하면 오류 생김
      let videoFiles = [
        'videos/login_video1.mp4','videos/login_video2.mp4','videos/login_video3.mp4','videos/login_video4.mp4','videos/login_video5.mp4',
      ];
      let randomIndex = Math.floor(Math.random() * videoFiles.length);
      let randomVideo = videoFiles[randomIndex];
      let videoElement = $('#background_video');

      videoElement.attr('src', randomVideo);
      videoElement[0].load();
    });

    let getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    let checkPW = RegExp(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*()_+]).{10,20}$/);
    $("#log_btn").click(function(){
      //이메일 공백 확인
      if($("#id").val() == ""){
        alert("이메일을 입력해주세요");
        $("#id").focus();
        return false;
      }
      //이메일 유효성 검사
      if(!getMail.test($("#id").val())){
        alert("잘못된 이메일 형태입니다.")
        $("#id").val("");
        $("#id").focus();
        return false;
      }
/*    //비밀번호 유효성검사
      if(!checkPW.test($("#password").val())){
       alert("비밀번호는 문자, 숫자, 특수문자 포함 10~20자 입니다");
       $("#password").val("");
       $("#password").focus();
       return false;
     }     
     //비밀번호 확인란 공백 확인
     if($("#password").val() == ""){
       alert("패스워드 확인란을 입력해주세요");
       $("#password").focus();
       return false;
     } */
    });
   
  </script>
  <script type="text/javascript">
  	var naver_id_login = new naver_id_login("YGOn5SW1QHo7CAPYA_6b", "http://localhost:9090/Myproject/callback.jsp");
  	var state = naver_id_login.getUniqState();
  	/* naver_id_login.setButton("white", 2,40); */
  	naver_id_login.setDomain("https://localhost:9090");
  	naver_id_login.setState(state);
  	naver_id_login.init_naver_id_login();
  </script>
  
	<script>
	Kakao.init('0f61f4b35316125690b371d5e5c744c3'); //발급받은 키 중 javascript키를 사용해준다.
	//카카오로그인
	function kakaoLogin(){
		Kakao.Auth.authorize({
			redirectUri: 'https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-code',
			state: 'userme',
		});
	}	
	
	</script> 
	<script>
		function onSignln(googleUser){
			var profile = googleUser.getBasicProfile();
			console.log("ID" + profile.getId());
			console.log("Name" + profile.getName());
			console.log("Email" + profile.getEmail());
		}
	</script>
</body>
</html>
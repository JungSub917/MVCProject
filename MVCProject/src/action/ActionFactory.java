package action;

public class ActionFactory {
	private static ActionFactory instacne = new ActionFactory();
	private ActionFactory() {
		
	}
	public static ActionFactory getInstance() {
		return instacne;
	}
	public Action getAction(String command) {
		Action action = null;
		switch(command) {
		/* 메인페이지 */
		case "MainPage" : action = new MainPageSearchAction(); break; 						// 메인페이지
		/* 로그인/회원가입 */
		case "LoginAction" : action = new LoginAction(); break;							// 로그인 동작
		case "LoginNaverAction" : action = new LoginNaverAction(); break;					// 로그인 네이버
		case "LogoutAction" : action = new LogoutAction(); break;						// 로그아웃 동작
		case "EmailCheck" : action = new EmailCheckAction(); break;						// 회원가입- 이메일 중복
		case "SignupAction" : action = new SignupAction(); break;						// 회원가입
		case "ChangePw" : action = new ChangePwAction(); break;							// 비밀번호 변경
		/* 탐색페이지 */
		case "natlist" : action = new NatListAction(); break;							// 탐색 메인페이지
		case "NatOrder" : action = new NatOrderAction(); break;							// 탐색 나라 나열 기능
		case "NatSearch" : action = new NatSearchAction(); break;						// 탐색 나라 검색 기능
		case "cityLikeBtn" : action = new cityLikeBtnAction(); break;						// 탐색 좋아요 기능
		/* 숙소페이지 */
		case "HotelMain" : action = new HotelMainAction(); break;						// 숙소 메인페이지
		case "HotelSub" : action = new HotelSubAction(); break;							// 숙소 서브페이지
		case "HotelOrder" : action = new HotelOrderAction(); break;						// 숙소 필터링 기능 
		case "HotelCount" : action = new HotelCountAction(); break;						// 숙소 페이징
		case "HotelReview" : action = new HotelReviewAction(); break;						// 숙소 리뷰페이지
		case "HrInfiniteScroll" : action = new HrInfiniteScrollAction(); break;					// 숙소 리뷰페이지 - 무한스크롤
		case "hotelReviewDetail" : action = new hrDetailDataAction(); break;					// 숙소 리뷰 상세 페이지
		case "HrReviewWrite" : action = new HrReviewWrite(); break;						// 숙소 review 글쓰기 
		case "HotelReviewReformPage" : action = new hrReviewReformAction(); break;				// 숙소 review 수정 페이지 이동
		case "HrReviewReform" : action = new HrReviewReform(); break;						// 숙소 review 수정
		case "HrReviewDelete" : action = new HrReviewDelete(); break;						// 숙소 review 삭제
		case "HrCommentWrite" : action = new HrCommentWrite(); break;						// 숙소 review 댓글 쓰기
		case "HrCommentDelete" : action = new HrCommentDelete(); break; 					// 숙소 review 댓글 삭제
		case "HrCommentUpdate" : action = new HrCommentUpdate(); break; 					// 숙소 review 댓글 수정
		} 
		return action;
	}
}

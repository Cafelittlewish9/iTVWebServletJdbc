import model.service.MemberService;

public class SelectByDemo {

	public static void main(String[] args) {
		
//		※ReportMemberDAOJdbc
		
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		ReportMemberVO bean = DAO.select(1);
//		System.out.println(bean);
//		
//		..........................................
		
		MemberService service = new MemberService();
		System.out.println(service.showMemberInfo("Pikachu", "A"));
		
		
		
	}

}

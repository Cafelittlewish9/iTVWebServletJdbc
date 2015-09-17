import model.dao.jdbc.ReportMemberDAOjdbc;

public class DeleteDemo {

	public static void main(String[] args) {
		
//		※ReportMemberDAOJdbc
//		int id = 10;
//		ReportMemberDAOJdbc DAO = new ReportMemberDAOJdbc();
//		boolean bool = DAO.delete(id);
//		System.out.println(bool);
		
//		..............................................

		ReportMemberDAOjdbc dao = new ReportMemberDAOjdbc();
		System.out.println(dao.delete(16));
		
		
	}

}

package model.service.restful;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dao.MemberDAO;
import model.dao.ReportMemberDAO;
import model.dao.jdbc.MemberDAOjdbc;
import model.dao.jdbc.ReportMemberDAOjdbc;
import model.vo.ReportMemberVO;
@Path("/reportMember")
public class ReportMemberRestful {
	private ReportMemberDAO dao;
	private MemberDAO dao2;

	public ReportMemberRestful() {
		this.dao = new ReportMemberDAOjdbc();
		this.dao2 = new MemberDAOjdbc();
	}

	public boolean addReportMember(int reportedMemberId, String reportReason) {
		ReportMemberVO bean = new ReportMemberVO();
		bean.setReportedMemberId(reportedMemberId);
		bean.setReportReason(reportReason);
		ReportMemberVO demo = dao.insert(bean);
		if (demo != null) {
			return true;
		} else {
			return false;
		}
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<ReportMemberVO> selectAll() {
		return dao.selectAll();
	}

	public boolean deleteReportMember(ReportMemberVO bean) {
		int result1 = dao2.switchSuspend(bean.getReportedMemberId(), true);
		boolean result2 = dao.delete(bean.getOrderId());
		if (result1 == 1 && result2) {
			return true;
		} else {
			return false;
		}
	}

}

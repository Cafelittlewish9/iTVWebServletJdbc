package model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.dao.ShowDAO;
import model.dao.jdbc.ShowDAOjdbc;
import model.vo.ShowVO;

public class ShowService {
	private ShowDAO dao;

	public ShowService() {
		this.dao = new ShowDAOjdbc();
	}

	public Collection<ShowVO> showList(int memberId) {
		Collection<ShowVO> list = dao.selectJoinVideo(memberId);
		return list;
	}

	public boolean addShow(ShowVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	// console 出現null pointer exception但移除成功
	public boolean removeShow(ShowVO bean) {
		boolean result = false;
		if (bean != null) {
			int temp = dao.insert(bean);
			if (temp == 1) {
				result = true;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ShowService service = new ShowService();
		for (ShowVO bean : service.showList(2)) {
			System.out.println(bean);
			// System.out.println(bean.getTitle());
		}
	}
}

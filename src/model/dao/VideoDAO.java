package model.dao;

import java.util.List;

import model.vo.VideoVO;

public interface VideoDAO {

	public List<VideoVO> selectByVideoTitle(String videoTitle);

	public List<VideoVO> selectByVideoClassName(String videoClassName);

	public List<VideoVO> selectAll();

	public boolean insert(VideoVO bean);

	public boolean update(String videoDescription, int videoId);

	public void update(long videoWatchTimes, int videoId);

	public boolean delete(int videoId);

}
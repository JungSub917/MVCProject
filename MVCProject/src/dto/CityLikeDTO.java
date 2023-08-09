package dto;

public class CityLikeDTO {
	private int lno;
	private int cityId;
	private int idx;
	
	public CityLikeDTO(int lno, int cityId, int idx) {
		this.lno = lno;
		this.cityId = cityId;
		this.idx = idx;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
	
}

package protocolPackage.egaofen;

public class EgaofenQuestionBody {

	private int paperId = 0;
	private int questionId = 0;
	private int questionSectionId = 0;
	private String studenSN = "";
	private String answer = "";
	private byte[] imageData = null;
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuestionSectionId() {
		return questionSectionId;
	}
	public void setQuestionSectionId(int questionSectionId) {
		this.questionSectionId = questionSectionId;
	}
	public String getStudenSN() {
		return studenSN;
	}
	public void setStudenSN(String studenSN) {
		this.studenSN = studenSN;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	
}

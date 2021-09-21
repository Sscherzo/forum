package board.bean;

import org.hibernate.validator.constraints.NotBlank;

public class BoardDTO {
  private int seq; // -- 글번호
  private String id; // -- 아이디
  private String subject; // -- 제목
  @NotBlank // -- null이나 ""나 빈 공백 문자열을 허용하지 않는다.
  private String content; // -- 내용
  private int hit; // -- 조회수
  private String logtime; // -- 작성일
  private int reply; // -- 코멘트
  private String fileName; // -- 파일이름
  private String original_fileName; // -- 오리지널 파일 이름

  public String getOriginal_fileName() {
    return original_fileName;
  }

  public void setOriginal_fileName(String original_fileName) {
    this.original_fileName = original_fileName;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public int getSeq() {
    return seq;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getHit() {
    return hit;
  }

  public void setHit(int hit) {
    this.hit = hit;
  }

  public String getLogtime() {
    return logtime;
  }

  public void setLogtime(String logtime) {
    this.logtime = logtime;
  }

  public int getReply() {
    return reply;
  }

  public void setReply(int reply) {
    this.reply = reply;
  }

}

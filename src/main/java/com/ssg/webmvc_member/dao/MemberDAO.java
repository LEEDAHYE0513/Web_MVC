package com.ssg.webmvc_member.dao;
//MemberDAO 의 listMembers() 메서드에서SQL 문으로 회원 정보를 조회후,
// 회원 정보를 MemberVO에 설정하여 반환합니다.
// ④ 다시 MemberController에서는 조회한 회원 정보를 회원목록창(listMembers.jsp)로 포워딩합니다.
// ⑤ 회원목록창(listMembers.jsp)에서 포워딩한 회원 정보를 목록으로출력합니다.

import com.ssg.webmvc_member.domain.MemberVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MemberDAO {

    // 회원정보 리스트
    public List<MemberVO> listAll() throws  Exception{
        String sql = "select * from mvc_member";

        @Cleanup Connection connection = ConnectionUtil.INSANCE.getConnection(); // @Cleanup 어노테이션을 사용하여 자동으로 연결을 해제
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);  //@Cleanup 어노테이션을 사용하여 자동으로 자원을 해제
        @Cleanup ResultSet rs =pstmt.executeQuery();

        List<MemberVO> list = new ArrayList<>();

        while (rs.next()){
            MemberVO vo = MemberVO.builder()
                    .id(rs.getString("id"))
                    .pw(rs.getString("pw"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .date(rs.getDate("date").toLocalDate())
                    .build();
            list.add(vo);
        } return list;
    }

    // 회원 추가
    public void insert(MemberVO memberVO) throws Exception{
        String sql = "insert into mvc_member(id,pw,name,email,date) values (?,?,?,?,now())";

        @Cleanup Connection conn = ConnectionUtil.INSANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, memberVO.getId());
        pstmt.setString(2,memberVO.getPw());
        pstmt.setString(3,memberVO.getName());
        pstmt.setString(4,memberVO.getEmail());
        pstmt.executeUpdate();
    }

    // 회원 수정
    public void updateOne(MemberVO memberVO) throws Exception{
        String sql = "update mvc_member set pw =?, name=?, email=? where id=? ";

        @Cleanup Connection conn = ConnectionUtil.INSANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,memberVO. getPw());
        pstmt.setString(2, memberVO.getName());
        pstmt.setString(3, memberVO.getEmail());
        pstmt.setString(4,memberVO.getId());
        pstmt.executeUpdate();
    }

    // 회원 삭제
    public void deleteOne(String id) throws Exception {
        String sql = "delete from mvc_member where id =?";

        @Cleanup Connection connection = ConnectionUtil.INSANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    // 수정과 삭제를 하기 위해서 id값을 매개변수로 받기 위한 메소드
    // 회원 아이디는 pk이니 하나의 객체로 사용할 수 있게 !
    public MemberVO selectOne(String id) throws Exception{
        String sql = "select * from mvc_member where id =? ";

        @Cleanup Connection connection = ConnectionUtil.INSANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1,id);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        MemberVO vo = MemberVO.builder()
                .id(rs.getString("id"))
                .pw(rs.getString("pw"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .date(rs.getDate("date").toLocalDate())
                .build();
        return vo;
    }

    //  회원의 아이디와 비밀번호, 이름을 전달받아, DB에 (가입된) 회원여부를 판별한다.
    //id, pw 로그인을 함
    //근데 register등록 를 하려면 id가 필요함
    //세션에 아이디를 유지함
    //아이디 말고도 이름, 패스워드도 필요함

    //로그인 시에는 아이디랑 비번을
    //컨트롤러 -> 서비스 -> 다오 -> DB -> 다오 -> 서비스 -> 컨트롤러
    //순으로 전달해서, 가져오는 값들은
    //아이디, 비번, 이름
    //3가지 값을 가져옴, 근데 값을 전달할때 DTO를 통해서 전달함(data transfer object)

    public MemberVO getWithPassword(String id, String pw)throws Exception{
        String query = "select id, pw, name from mvc_member where id = ? and pw = ? ";
        MemberVO memberVO = null;


        @Cleanup Connection connection = ConnectionUtil.INSANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, id);
        pstmt.setString(2, pw);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        memberVO = MemberVO.builder()
                .id(rs.getString(1))
                .pw(rs.getString(2))
                .name(rs.getString(3))
                .build();
        return memberVO;
    }

}

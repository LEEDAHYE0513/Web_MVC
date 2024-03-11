package com.ssg.webmvc_member.service;

import com.ssg.webmvc_member.dao.MemberDAO;
import com.ssg.webmvc_member.domain.MemberVO;
import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.util.MapperUtil;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum MemberService {
    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;

    // MemberService는 VO, DTO 사용하는 구조 !
    // MedelMapper와  MemberDAO를 이용하도록 구성

     MemberService(){
         memberDAO = new MemberDAO();
         modelMapper = MapperUtil.INSTANCE.get();
     }


     // 회원정보 리스트 보여주는 메소드
     public List<MemberDTO> memberList() throws Exception {
         List<MemberVO> voList = memberDAO.listAll();

         List<MemberDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, MemberDTO.class)).collect(Collectors.toList());
         return dtoList;
     }

     // 회원 추가 메소드
    public void add(MemberDTO memberDTO) throws Exception{
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        System.out.println("memberVO" + memberVO);
        memberDAO.insert(memberVO);
    }


    // 회원리스트에서 회원정보 수정 메소드
    public void modify(MemberDTO memberDTO) throws Exception{
        System.out.println("member modify service" + memberDTO);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.updateOne(memberVO);
    }

    // 회원리스트에서 회원정보 삭제 메소드
    public void remove(String id) throws Exception{
        System.out.println("member remove service" + id);
        memberDAO.deleteOne(id);
    }

    // id에 해당하는 객체를 가져온느데 이걸 dto로 변환 메소드
    public MemberDTO get(String id) throws Exception
    {
        MemberVO vo = memberDAO.selectOne(id);
        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);
        return dto;
    }

    // 로그인 기능 메소드
    public MemberDTO login(String id, String pw) throws Exception{
        MemberVO memberVO = memberDAO.getWithPassword(id, pw);//vo는 디비에서 값을 가져오기 위해서
        MemberDTO dto = modelMapper.map(memberVO, MemberDTO.class);//계층간의 데이터를 전달하고 전달받을땐 Dto 써야 하므로 dto에 담아줘야한다.
        return dto;
    }
}

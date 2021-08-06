package com.system.dao;

import com.system.domain.Message;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    @Select("select count(*) from message where type=1 and active=0 and `to`=#{username}")
    public Integer getUnreadMessageCount(String username);

    @Select("select `from`,`to`,`content`,`time`,`type` from message where (`from`=#{from} and `to`=#{username}) or" +
            "(`from`=#{username} and `to`=#{from}) order by time asc")
    List<Message> getOldMessages(@Param("username") String username, @Param("from") String from);

    @Select("insert into message(`from`,`to`,content,time,type) values(#{from},#{to},#{content},#{time},#{type})")
    void saveMessage(Message msg);
}

package cn.edu.bupt.cac.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
// 请求类
public class Request {
    private String roomId; // 请求房间ID
    private String type; // 请求类型（开始送风/停止送风）
    private String fanSpeed; // 风速（高/中/低）
    private int targetTemp; // 目标温度
    private BigDecimal temperature; // 房间温度
}

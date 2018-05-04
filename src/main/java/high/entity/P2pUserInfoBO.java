/*************************************************************************
 * @system name：  :信人在线
 * @Author: 李宏伟 207416511@qq.com
 * @Date: 2018年4月24日 下午6:07:21
 * @(c) Copyright 上海达信财富
 **************************************************************************/

package high.entity;

import java.io.Serializable;

/**
 * @包名 :com.fuan.mwp.web.BO
 * @文件名 :P2pUserBO.java TODO 类作用：接收p2p异步回调数据
 * @系统名称 : 现金贷
 * @Author: 李宏伟
 * @Date: 2018年4月24日 下午6:07:21
 * @版本号 :v0.0.01
 */
public class P2pUserInfoBO implements Serializable {

	/**
	 * <code>serialVersionUID</code> of comment
	 */
	private static final long serialVersionUID = -7878378429046215324L;

	/**
	 * 响应编码
	 */
	private String retCode;
	/**
	 * 响应信息
	 */
	private String retInfo;
	/**
	 * p2p用户编号
	 */
	private String userId;
	/**
	 * 商户客户号
	 */
	private String merCustId;
	/**
	 * 用户号
	 */
	private String usrId;
	/**
	 * 真实姓名
	 */
	private String usrName;
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 证件号码
	 */
	private String idNo;
	/**
	 * 用户email
	 */
	private String usrEmail;
	/**
	 * 手机号
	 */
	private String usrMp;
	/**
	 * 开户银行账号
	 */
	private String cardId;
	/**
	 * 开户银行代号
	 */
	private String openBankId;
	/**
	 * 审核过程中的状态： I： 初始 T：提交 P：审核中 R：审核拒绝 F：开户失败 K： 开户中 Y：开户成功
	 */
	private String auditStat;
	/**
	 * 系统标示
	 */
	private String systemSource;
	/**
	 * 签名
	 */
	private String signature;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetInfo() {
		return retInfo;
	}

	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMerCustId() {
		return merCustId;
	}

	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getOpenBankId() {
		return openBankId;
	}

	public void setOpenBankId(String openBankId) {
		this.openBankId = openBankId;
	}

	public String getAuditStat() {
		return auditStat;
	}

	public void setAuditStat(String auditStat) {
		this.auditStat = auditStat;
	}

	public String getUsrMp() {
		return usrMp;
	}

	public void setUsrMp(String usrMp) {
		this.usrMp = usrMp;
	}

	public String getSystemSource() {
		return systemSource;
	}

	public void setSystemSource(String systemSource) {
		this.systemSource = systemSource;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}

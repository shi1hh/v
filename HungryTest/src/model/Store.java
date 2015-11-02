package model;

import java.io.Serializable;

import com.example.hungrytest.R.string;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

import android.R.bool;
import android.R.integer;
import android.widget.TextView;

public class Store extends BmobObject  implements Serializable {

	String nameString;
	int logoid;
	int dispatch;
	int minPay;
	String speed;
	Integer avgSpeed;
	float favourValue;
	boolean isWoke;
	private String introduString;
	private String address;
	private String worktime;
	private String announcement;
	BmobFile logo;
	Integer praise;
	private Float serviceValue;
	private Float goodsValue;

	
	public void setWorkState(boolean workstate) {
		this.isWoke=workstate;
	}
	public void setFavourValue(float favourvalue) {
		this.favourValue=favourvalue;
	}
	public void setName(String nameString) {
		this.nameString=nameString;
	}
	public void setLogoid(int logoid) {
		this.logoid=logoid;
	}
	public void setMinPay(int minPay) {
		this.minPay=minPay;
	}
	public void setSpeed(String speed) {
		this.speed=speed;
	}
	public void setDispatch(int dispatch) {
		this.dispatch=dispatch;
	}
	public String getName() {
		return this.nameString;
	}
	public int getLogoId() {
		return this.logoid;
	}
	public int getMinPay() {
		return this.minPay;
	}
	public String getSpeed() {
		return this.speed;
	}
	public int getDispatch() {
		return this.dispatch;
	}
	public float getFavourValue() {
		return this.favourValue;
	}
	public boolean getWorkState() {
		return this.isWoke;
	}
	public void setIntrodu(String introduString) {
		this.introduString=introduString;
	}
	public String getIntrodu() {
		return this.introduString;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public String getAddress() {
		return this.address;
	}
	public void setWorkTime(String worktime)
	{
		this.worktime=worktime;
	}
    public String getWorkTime() {
		return this.worktime;
	}
    public void setAnnouncement(String announcement)
    {
    	this.announcement=announcement;
    }
    public String getAnnouncement() {
		return this.announcement;
	}
	public void setAvgSpeed(Integer avgspeed) {
		this.avgSpeed=avgspeed;
	}
	public Integer getAvgSpeed() {
		return this.avgSpeed;
	}
	public void setLogo(BmobFile url) {
		this.logo=url;
	}
	public BmobFile getLogo() {
		return this.logo;
	}
	public void setPraise(Integer praise) {
		this.praise=praise;
	}
	public Integer getPraise() {
		return this.praise;
	}
	public Float getServiceVal()
	{
		return this.serviceValue;
	}
	public void setServiceVal(Float serviceVal)
	{
		this.serviceValue=serviceVal;
	}
	public Float getGoodsVal()
	{
		return this.goodsValue;
	}
	public void setGoodsVal(Float goodsValue)
	{
		this.goodsValue=goodsValue;
	}

}


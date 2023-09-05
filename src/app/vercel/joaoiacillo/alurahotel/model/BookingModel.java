package app.vercel.joaoiacillo.alurahotel.model;

import java.sql.Date;

public class BookingModel {

	private int id;
	private String number;
	private Date checkinDate, checkoutDate;
	private double price;
	private String paymentMethod;
	private int guestId;
	
	public BookingModel(Date checkinDate, Date checkoutDate, double price, String paymentMethod, int guestId) {
		this.number = this.getNumber();
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.guestId = guestId;
	}

	public BookingModel(int id, String number, Date checkinDate, Date checkoutDate, double price, String paymentMethod,
			int guestId) {
		this.id = id;
		this.number = number;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.guestId = guestId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		if (this.number != null) return this.number;
		
		Long now = new java.util.Date().getTime();
		this.number = Long.toString(now, 16);
		
		return number;
	}

	/**
	 * @return the checkinDate
	 */
	public Date getCheckinDate() {
		return checkinDate;
	}

	/**
	 * @return the checkoutDate
	 */
	public Date getCheckoutDate() {
		return checkoutDate;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @return the guestId
	 */
	public int getGuestId() {
		return guestId;
	}
	
}

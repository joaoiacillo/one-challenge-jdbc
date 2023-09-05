package app.vercel.joaoiacillo.alurahotel.model;

import java.sql.Date;

public class GuestModel {
	
	private int id;
	private String firstName, lastName;
	private String phoneNumber;
	private String nationality;
	private Date birthday;
	
	public GuestModel(String firstName, String lastName, String phoneNumber, String nationality, Date birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.nationality = nationality;
		this.birthday = birthday;
	}

	public GuestModel(int id, String firstName, String lastName, String phoneNumber, String nationality,
			Date birthday) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.nationality = nationality;
		this.birthday = birthday;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	
}

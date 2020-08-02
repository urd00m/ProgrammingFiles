package contact;

public class Contact {

	private String firstName;
	private String lastName;

	private String homePhoneNum;
	private String mobilePhoneNum;

	private String emailAddress;

	private String streetAddress;
	private String cityAddress;
	private String stateAddress;
	private String zipCode;

	private String secretInfo;
	private String password;

	// This next method is automatically called when a new Contact is created

	public Contact(String first, String last) {
		firstName = first;
		lastName = last;
		homePhoneNum = "";
		mobilePhoneNum = "";
		emailAddress = "";
		streetAddress = "";
		cityAddress = "";
		stateAddress = "";
		zipCode = "";
		secretInfo = "";
		password = "pass";

	}

	// The next group of methods change the information stored in Contact

	public void changeFirstName(String str) {
		firstName = str;
	}

	public void changeLastName(String str) {
		lastName = str;
	}

	public void changeHomePhoneNum(String str) {
		homePhoneNum = str;
	}

	public void changeMobilePhoneNum(String str) {
		mobilePhoneNum = str;
	}

	public void changeEmailAddress(String str) {
		emailAddress = str;
	}

	public void changeStreetAddress(String str) {
		streetAddress = str;
	}

	public void changeCityAddress(String str) {
		cityAddress = str;
	}

	public void changeStateAddress(String str) {
		stateAddress = str;
	}

	public void changeZipCode(String str) {
		zipCode = str;
	}

	public boolean changeSecretInfo(String str, String pwd) {
		boolean changed = false;
		if (pwd.equals(password)) {
			secretInfo = str;
			changed = true;
		}

		return changed;
	}

	public boolean changePassword(String str, String pwd) {
		boolean changed = false;
		if (pwd.equals(password)) {
			password = str;
			changed = true;
		}

		return changed;
	}

	// The following group of methods lets you access the information

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getHomePhoneNum() {
		return homePhoneNum;
	}

	public String getMobilePhoneNum() {
		return mobilePhoneNum;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getCityAddress() {
		return cityAddress;
	}

	public String getStateAddress() {
		return stateAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getSecretInfo(String pwd) {
		String info = "ACCESS DENIED!";
		if (pwd.equals(password)) {
			info = secretInfo;
		}

		return info;
	}

	public String getPassword() {
		String response = "ACCESS DENIED!";
		if (password.equals("pass")) {
			response = "pass";
		}
		return response;
	}

	// Other methods may be added...

	public boolean isSamePerson(Contact person) {
		boolean same = false;
		if (person.getFirstName().equals(getFirstName()) && person.getLastName().equals(getLastName()))
			same = true;
		return same;
	}
}

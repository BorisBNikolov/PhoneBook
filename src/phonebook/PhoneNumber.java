package phonebook;

public class PhoneNumber {
    private String phoneNumber;
    private int callsCount;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.callsCount = 0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCallsCount() {
        return callsCount;
    }

    public void setCallsCount(int callsCount) {
        this.callsCount = callsCount;
    }

    public int compareTo(PhoneNumber phoneNumber) {
        return Integer.compare(this.callsCount, phoneNumber.getCallsCount());
    }
}

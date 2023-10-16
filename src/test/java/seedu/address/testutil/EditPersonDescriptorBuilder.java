package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing
     * {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setBankAccount(person.getBankAccount());
        descriptor.setJoinDate(person.getJoinDate());
        descriptor.setSalary(person.getSalary());
        descriptor.setAnnualLeave(person.getAnnualLeave());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code BankAccount} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withBankAccount(String bankAccount) {
        descriptor.setBankAccount(new BankAccount(bankAccount));
        return this;
    }

    /**
     * Sets the {@code JoinDate} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withJoinDate(String joinDate) {
        descriptor.setJoinDate(new JoinDate(joinDate));
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withSalary(String salary) {
        descriptor.setSalary(new Salary(salary));
        return this;
    }

    /**
     * Sets the {@code AnnualLeave} of the {@code EditPersonDescriptor} that we are
     * building.
     */
    public EditPersonDescriptorBuilder withAnnualLeave(String annualLeave) {
        descriptor.setAnnualLeave(new AnnualLeave(annualLeave));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}

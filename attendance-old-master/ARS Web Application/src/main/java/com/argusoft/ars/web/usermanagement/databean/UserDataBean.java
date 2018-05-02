/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argusoft.ars.web.usermanagement.databean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author momodi
 */
@ManagedBean(name = "userDataBean")
@ViewScoped
public class UserDataBean implements Serializable {

    private Long id;
    @Length(min = 4, message = "Enter valid userid")
    private String userId;
    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\s.]*)?$", message = "Enter valid first name")
    private String firstName;
    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\s.]*)?$", message = "Enter valid middle name")
    private String middleName;
    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\s.]*)?$", message = "Enter valid last name")
    private String lastName;
    @Length(min = 8, message = "Password should be of 8 characters.")
    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\d.]*)?$", message = "Enter valid password")
    private String password;
    @Pattern(regexp = "^([a-zA-Z][a-zA-Z\\d.]*)?$", message = "Enter valid confirm password")
    @Length(min = 8, message = "Confirm Password should be of 8 characters.")
    private String rePassword;
    @Email(message = "Enter valid email id")
    private String email;
    @Email(message = "Enter valid email id")
    private String alternateEmail;
    private String preferredLanguage = "EN";
    private String gender;
    private Date dob;
    @Pattern(regexp = "[0-9]*$", message = "Enter valid phone no")
    @Length(min = 10, max = 10, message = "Enter valid phone no")
    private String phoneNo;
    @Pattern(regexp = "[0-9]*$", message = "Enter valid residential no")
    private String alternatePhoneNo;
    private String status;
    private String oldPassword;
    private String newPassword;
    private Boolean passwordCheck;
    private String displayName;
    private String type;
    private Date lastLoginTime;
    private Long contactReference;
    private Long role;
    private String userRole;
    private String timeZone;
    private String displayRole;
    private List<RoleDataBean> userRoleList = new ArrayList<RoleDataBean>();
    private Boolean isActive;
    @Range(min = 1, message = "{Selectstate}")
    private Integer parent;
    @Range(min = 1, message = "{Selectdistrict}")
    private Integer district;
    @Range(min = 1, message = "{Selectblock}")
    private Integer block;
    @Length(max = 300, message = "{Entervalidaddress}")
    private String address;
    private Long custom1;
    @Pattern(regexp = "[0-9]*$", message = "{EntervalidIMEIno}")
    @Length(min = 15, message = "{EntervalidIMEIno}")
    private String imeiNo;
    private Date givenDate;
    private String phoneStatus;
    private String subType;
    private String blocks;
    private String districts;
    private String states;
    @Range(min = 1, message = "{Pleaseselectatleastoneareaofintervention}")
    private int listSize;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{Selectpco}")
    private String pcoId;
    private Long userMobileId;
    @Past(message = "{Donotenterfuturedates}")
    private Date missedDate;
    @Length(max = 250, message = "{Entervalidspecify}")
    private String reason;
    private String pcoName;
    private String ngoName;
    private Integer ngoId;
    private Date createdOn;
    @Pattern(regexp = "[0-9]*$", message = "{Entervalidaccountno}")
    private String accountNo;
    private String pcoUserId;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{Selectorw}")
    private String orwId;
    @Range(min = 0, message = "{Selectstate}")
    private Integer selectedState;
    private String captchaResponse;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{SelectoldORW}")
    private String oldORW;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "{SelectnewORW}")
    private String newORW;
    private String confirmNewPassword;
    private List<Long> selectedRoleList;
    private String empId;
    private Long depId;
    private Long shiftId;
    private Long cardIdPk;
    private Date doj;
    private Date doc;
    private String empType;
    private Long desgId;
    private String departmnetName;
    private String designationName;
    private String ShiftName;
    private Long cardId;
    private Boolean isAdmin;
    private Long tempCardIdPk;
    private Date dor;
    private String tempEmpType;

    public String getTempEmpType() {
        return tempEmpType;
    }

    public void setTempEmpType(String tempEmpType) {
        this.tempEmpType = tempEmpType;
    }

    public Date getDor() {
        return dor;
    }

    public void setDor(Date dor) {
        this.dor = dor;
    }

    public Long getTempCardIdPk() {
        return tempCardIdPk;
    }

    public void setTempCardIdPk(Long tempCardIdPk) {
        this.tempCardIdPk = tempCardIdPk;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getShiftName() {
        return ShiftName;
    }

    public void setShiftName(String ShiftName) {
        this.ShiftName = ShiftName;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getDepartmnetName() {
        return departmnetName;
    }

    public void setDepartmnetName(String departmnetName) {
        this.departmnetName = departmnetName;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public Long getDesgId() {
        return desgId;
    }

    public void setDesgId(Long desgId) {

        this.desgId = desgId;
    }

    public Long getCardIdPk() {
        return cardIdPk;
    }

    public void setCardIdPk(Long cardIdPk) {
        System.out.println("cardIdSetter:" + cardIdPk);
        this.cardIdPk = cardIdPk;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public Date getDoc() {
        return doc;
    }

    public void setDoc(Date doc) {
        this.doc = doc;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        if ("select".equals(empType)) {
            this.empType = null;
        } else {
            this.empType = empType;
        }
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getSelectedRoleList() {
        return selectedRoleList;
    }

    public void setSelectedRoleList(List<Long> selectedRoleList) {
        this.selectedRoleList = selectedRoleList;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getNewORW() {
        return newORW;
    }

    public void setNewORW(String newORW) {
        this.newORW = newORW;
    }

    public String getOldORW() {
        return oldORW;
    }

    public void setOldORW(String oldORW) {
        this.oldORW = oldORW;
    }

    public String getCaptchaResponse() {
        return captchaResponse;
    }

    public void setCaptchaResponse(String captchaResponse) {
        this.captchaResponse = captchaResponse;
    }

    public Integer getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(Integer selectedState) {
        this.selectedState = selectedState;
    }

    public String getOrwId() {
        return orwId;
    }

    public void setOrwId(String orwId) {
        this.orwId = orwId;
    }

    public String getPcoUserId() {
        return pcoUserId;
    }

    public void setPcoUserId(String pcoUserId) {
        this.pcoUserId = pcoUserId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPcoName() {
        return pcoName;
    }

    public void setPcoName(String pcoName) {
        this.pcoName = pcoName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getMissedDate() {
        return missedDate;
    }

    public void setMissedDate(Date missedDate) {
        this.missedDate = missedDate;
    }

    public Long getUserMobileId() {
        return userMobileId;
    }

    public void setUserMobileId(Long userMobileId) {
        this.userMobileId = userMobileId;
    }

    public String getPcoId() {
        return pcoId;
    }

    public void setPcoId(String pcoId) {
        this.pcoId = pcoId;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public String getBlocks() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public String getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(String phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public Long getCustom1() {
        return custom1;
    }

    public void setCustom1(Long custom1) {
        this.custom1 = custom1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getAlternatePhoneNo() {
        return alternatePhoneNo;
    }

    public void setAlternatePhoneNo(String alternatePhoneNo) {
        this.alternatePhoneNo = alternatePhoneNo;
    }

    public List<RoleDataBean> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<RoleDataBean> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public String getDisplayRole() {
        return displayRole;
    }

    public void setDisplayRole(String displayRole) {
        this.displayRole = displayRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getContactReference() {
        return contactReference;
    }

    public void setContactReference(Long contactReference) {
        this.contactReference = contactReference;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getDob() {
        System.out.println("Dob getter : " + this.dob);
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
        System.out.println("Dob setter : " + this.dob);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(Boolean passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDataBean other = (UserDataBean) obj;
        if ((this.userId == null) ? (other.userId != null) : !this.userId.equals(other.userId)) {
            return false;
        }
        if (this.contactReference != other.contactReference && (this.contactReference == null || !this.contactReference.equals(other.contactReference))) {
            return false;
        }
        return true;
    }

    public Integer getNgoId() {
        return ngoId;
    }

    public void setNgoId(Integer ngoId) {
        this.ngoId = ngoId;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        hash = 89 * hash + (this.contactReference != null ? this.contactReference.hashCode() : 0);
        return hash;
    }

    public void setDataBean(UserDataBean userDataBean) {
        System.out.println("====>Id:" + userDataBean.getId());
        this.id = userDataBean.getId();
        this.empId = userDataBean.getEmpId();
        this.userId = userDataBean.getUserId();
        this.firstName = userDataBean.getFirstName();
        this.middleName = userDataBean.getMiddleName();
        this.lastName = userDataBean.getLastName();
        this.password = userDataBean.getPassword();
        this.email = userDataBean.getEmail();
        this.gender = userDataBean.getGender();
        this.dob = userDataBean.getDob();
        this.phoneNo = userDataBean.getPhoneNo();
        this.alternatePhoneNo = userDataBean.getAlternatePhoneNo();
        this.oldPassword = userDataBean.getOldPassword();
        this.newPassword = userDataBean.getNewPassword();
        this.displayName = userDataBean.getDisplayName();
        this.type = userDataBean.getType();
        this.lastLoginTime = userDataBean.getLastLoginTime();
        this.address = userDataBean.getAddress();
        this.confirmNewPassword = userDataBean.getConfirmNewPassword();
        this.depId = userDataBean.getDepId();
        this.shiftId = userDataBean.getShiftId();
        this.cardIdPk = userDataBean.getCardIdPk();
        this.doj = userDataBean.getDoj();
        this.doc = userDataBean.getDoc();
        this.empType = userDataBean.getEmpType();
        this.desgId = userDataBean.getDesgId();
        this.departmnetName = userDataBean.getDepartmnetName();
        this.designationName = userDataBean.getDesignationName();
        this.ShiftName = userDataBean.getShiftName();
        this.cardId = userDataBean.getCardId();
        this.tempCardIdPk = userDataBean.getTempCardIdPk();
        this.alternateEmail = userDataBean.getEmail();
    }
}

package com.atits.entity;

import javax.persistence.*;

/**
 *简历
 * @author zys
 */
@Entity(name = "t_profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;//对应用户

    private String name;//姓名

    private int phoneNumber;//个人电话

    private int officePhone;//办公室电话

    private String email;//邮箱

    private String department;//部门

    private String sex;// 性别

    private String nation;// 民族

    private String birthdate;// 出生日期

    private String politicsStatus;// 政治面貌

    private String education;// 文化程度 education

    private String degree;// 学位 degree

    private String graduateInstitutions;// 毕业学校

    private String graduationDate;// 毕业时间 Graduation Date

    private String major; // 所学专业 major

    private String undertake;// 从事专业

    private String administrativeFunction;// 行政职务 administrative function

    private String technicalTitle;// 技术职称 technical title

    private String ministerialExpert;// 部级专家 ministerial expert

    private String provincialExpert;// 省级专家 provincial expert

    private String postalCode;// 邮政编码 postal code

    private String address;// 通讯地址 address

    private String professionalAffiliations;// 社会兼职情况 Professional affiliations

    private String professionalExpertise; // 专业特长 professional expertise


    @Column(length = 100000)
    private String participate;// 主持或参与重大课题或重大项目情况（近十年）

    @Column(length = 100000)
    private String achievement; // 主要业绩

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user=user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(int officePhone) {
        this.officePhone = officePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getGraduateInstitutions() {
        return graduateInstitutions;
    }

    public void setGraduateInstitutions(String graduateInstitutions) {
        this.graduateInstitutions = graduateInstitutions;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUndertake() {
        return undertake;
    }

    public void setUndertake(String undertake) {
        this.undertake = undertake;
    }

    public String getAdministrativeFunction() {
        return administrativeFunction;
    }

    public void setAdministrativeFunction(String administrativeFunction) {
        this.administrativeFunction = administrativeFunction;
    }

    public String getTechnicalTitle() {
        return technicalTitle;
    }

    public void setTechnicalTitle(String technicalTitle) {
        this.technicalTitle = technicalTitle;
    }

    public String getMinisterialExpert() {
        return ministerialExpert;
    }

    public void setMinisterialExpert(String ministerialExpert) {
        this.ministerialExpert = ministerialExpert;
    }

    public String getProvincialExpert() {
        return provincialExpert;
    }

    public void setProvincialExpert(String provincialExpert) {
        this.provincialExpert = provincialExpert;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfessionalAffiliations() {
        return professionalAffiliations;
    }

    public void setProfessionalAffiliations(String professionalAffiliations) {
        this.professionalAffiliations = professionalAffiliations;
    }

    public String getProfessionalExpertise() {
        return professionalExpertise;
    }

    public void setProfessionalExpertise(String professionalExpertise) {
        this.professionalExpertise = professionalExpertise;
    }

    public String getParticipate() {
        return participate;
    }

    public void setParticipate(String participate) {
        this.participate = participate;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", officePhone=" + officePhone +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", education='" + education + '\'' +
                ", degree='" + degree + '\'' +
                ", graduateInstitutions='" + graduateInstitutions + '\'' +
                ", graduationDate='" + graduationDate + '\'' +
                ", major='" + major + '\'' +
                ", undertake='" + undertake + '\'' +
                ", administrativeFunction='" + administrativeFunction + '\'' +
                ", technicalTitle='" + technicalTitle + '\'' +
                ", ministerialExpert='" + ministerialExpert + '\'' +
                ", provincialExpert='" + provincialExpert + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", address='" + address + '\'' +
                ", professionalAffiliations='" + professionalAffiliations + '\'' +
                ", professionalExpertise='" + professionalExpertise + '\'' +
                ", participate='" + participate + '\'' +
                ", achievement='" + achievement + '\'' +
                '}';
    }
}

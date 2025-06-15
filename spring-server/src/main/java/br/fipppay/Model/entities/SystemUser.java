package br.fipppay.Model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "system_user")
public class SystemUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_cpf")
    private String cpf;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_dt_nasc")
    private LocalDate dt_nasc;
    @Column(name = "user_address")
    private String address;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_level")
    private String level;

    public SystemUser()
    {
        this(0L,"","","",null,"","","");
    }

    public SystemUser (Long id, String name, String cpf, String email, LocalDate dt_nasc, String address, String password, String level) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.dt_nasc = dt_nasc;
        this.address = address;
        this.password = password;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(LocalDate dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

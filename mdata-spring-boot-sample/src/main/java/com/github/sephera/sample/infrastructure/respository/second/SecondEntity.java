package com.github.sephera.sample.infrastructure.respository.second;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "second")
public class SecondEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String data;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}

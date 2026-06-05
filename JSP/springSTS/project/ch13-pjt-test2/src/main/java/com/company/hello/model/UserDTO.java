package com.company.hello.model;

public class UserDTO {
    /* 필드 선언 */
    private String userId;   /* 아이디 */
    private String password; /* 비밀번호 */
    private String name;     /* 성명 */
    private String email;    /* 메일 주소 */
    private String phone;    /* 전화번호 */
    private int    point;    /* 가입 후 부여된 포인트 */

    /* 생성자 선언 */

    /* 메소드 선언 - getter / setter */
    public String getUserId()                { return userId;           }
    public void   setUserId(String userId)   { this.userId = userId;    }

    public String getPassword()                  { return password;             }
    public void   setPassword(String password)   { this.password = password;    }

    public String getName()              { return name;         }
    public void   setName(String name)   { this.name = name;    }

    public String getEmail()               { return email;          }
    public void   setEmail(String email)   { this.email = email;    }

    public String getPhone()               { return phone;          }
    public void   setPhone(String phone)   { this.phone = phone;    }

    public int  getPoint()           { return point;          }
    public void setPoint(int point)  { this.point = point;    }
}

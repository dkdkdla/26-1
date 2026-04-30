<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>박민재 | 자기소개</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #e0f7fa 0%, #e8f5e9 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .card {
            background: #ffffff;
            border-radius: 24px;
            padding: 50px 60px;
            max-width: 520px;
            width: 90%;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.08);
            text-align: center;
            animation: fadeUp 0.7s ease;
        }

        @keyframes fadeUp {
            from { opacity: 0; transform: translateY(30px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        .avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            background: linear-gradient(135deg, #4dd0e1, #66bb6a);
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 24px;
            font-size: 42px;
        }

        .name {
            font-size: 32px;
            font-weight: 700;
            color: #2e2e2e;
            letter-spacing: -0.5px;
            margin-bottom: 6px;
        }

        .role-badge {
            display: inline-block;
            background: linear-gradient(135deg, #4dd0e1, #66bb6a);
            color: #fff;
            font-size: 13px;
            font-weight: 600;
            padding: 5px 16px;
            border-radius: 20px;
            margin-bottom: 32px;
            letter-spacing: 0.5px;
        }

        .divider {
            border: none;
            height: 1px;
            background: #f0f0f0;
            margin: 0 auto 28px;
            width: 60%;
        }

        .section-title {
            font-size: 11px;
            font-weight: 700;
            color: #aaa;
            text-transform: uppercase;
            letter-spacing: 1.5px;
            margin-bottom: 10px;
        }

        .info-row {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            font-size: 15px;
            color: #555;
            margin-bottom: 24px;
        }

        .info-row .icon {
            font-size: 18px;
        }

        .contact-btn {
            display: inline-block;
            margin-top: 10px;
            padding: 12px 36px;
            background: linear-gradient(135deg, #4dd0e1, #66bb6a);
            color: #fff;
            font-size: 15px;
            font-weight: 600;
            border-radius: 50px;
            text-decoration: none;
            letter-spacing: 0.3px;
            transition: transform 0.2s, box-shadow 0.2s;
            box-shadow: 0 4px 15px rgba(77, 208, 225, 0.35);
        }

        .contact-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(77, 208, 225, 0.45);
        }

        .footer {
            margin-top: 36px;
            font-size: 12px;
            color: #ccc;
        }
    </style>
</head>
<body>

<%
    String name    = "박민재";
    String role    = "대학생";
    String contact = "010-1234-5678";
%>

<div class="card">

    <div class="avatar">🎓</div>

    <div class="name"><%= name %></div>
    <div class="role-badge"><%= role %></div>

    <hr class="divider">

    <div class="section-title">연락처</div>
    <div class="info-row">
        <span class="icon">📱</span>
        <span><%= contact %></span>
    </div>

    <a class="contact-btn" href="tel:<%= contact.replace("-", "") %>">
        연락하기
    </a>

    <div class="footer">
        &copy; <%= new java.util.Date().getYear() + 1900 %> <%= name %>
    </div>

</div>

</body>
</html>

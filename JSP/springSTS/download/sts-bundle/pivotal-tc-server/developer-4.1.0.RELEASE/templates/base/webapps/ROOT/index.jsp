<%@ page import="org.apache.catalina.util.ServerInfo" session="false" %>
<!DOCTYPE HTML>
<html>

<head>
	<link rel="shortcut icon" href="http://www.pivotal.io/sites/all/themes/gopo13/images/favicon.ico" type="image/x-icon">
	<title>VMware tc Server &#8212; Developer Edition</title>
	<link type="text/css" rel="stylesheet" href="splash.css">
</head>

<body>
	<div id="container">
		<!-- This page was released with VMware tc Server Developer Edition 4.1.0.RELEASE -->
		<!-- Header -->
		<div id="hdr"><span class="utility"><a href="https://network.pivotal.io/products/pivotal-app-suite" title="VMware tc Server">VMware tc Server</a></span><a href="http://www.pivotal.io/" title="VMware"><h1>VMware</h1></a></div>
		<div class="clearfix"></div>

		<!-- Body -->
		<div id="content">
    		<div id="intro">
				<!--<h4>VMware</h4>-->
				<h2>VMware tc Server &#8212; Developer Edition</h2>
				<h3 class="title">Congratulations! You have successfully setup and started VMware tc Server.  You are ready to go!</h3>
			</div>

			<div class="bodyrule"><hr /></div>

			<div>
				<p>This is the default VMware tc Server Runtime home page. It is located on the local filesystem at:</p>
				<span class="code">$TC_RUNTIME_INSTANCE_HOME/webapps/ROOT/index.jsp</span>
				<p>where <span class="code">$TC_RUNTIME_INSTANCE_HOME</span> is the root of the tc Runtime instance directory.</p>
				<p>&nbsp;</p>
				<div id="links">
				<h3>For additional information about VMware tc Server, see the following links:</h3>
				<ul>
					<li><a href="https://network.pivotal.io/products/pivotal-tcserver">VMware tc Server Product Details</a><br />The official home for the VMware tc Server.</li>
					<li><a href="https://docs.pivotal.io/tcserver/index.html">VMware tc Server Documentation </a><br />Find out what it does, how to use it, and other useful information.</li>
					<li><a href="https://stackoverflow.com/questions/tagged/tcserver">VMware tc Server Product Community</a><br />We are active on StackOverflow, so you can ask questions and get answers from VMware experts and experienced community members.</li>
					<li><a href="https://www.spring.io/tools">Spring Tool Suite (STS)</a><br />Go here to download the latest STS release with VMware tc Server.</li>
					<li><a href="https://www.pivotal.io/contact">VMware tc Server Inquiries</a><br />Have a question about VMware tc Server? Contact us.</li>
					<li><a href="mailto:tcserver@pivotal.io">Email questions to us</a><br></li>
				</ul>
				</div>
			</div>
		</div>

		<div class="clearfix"></div>
		<div id="versions">
			VMware tc Server Developer Edition<br/>
			<% out.println(ServerInfo.getServerInfo()); %>
		</div>

		<div id="ftr">&copy; 2020 VMware, Inc. All rights reserved.</div>
	</div>

</body>
</html>

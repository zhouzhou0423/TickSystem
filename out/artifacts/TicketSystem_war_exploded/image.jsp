<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"
	pageEncoding="utf-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	try {
		//在内存中创建图象
		int width = 60; //图像的宽度
		int height = 20; //图像的高度
		BufferedImage image = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB);

		//获取图形上下文
		Graphics g = image.getGraphics();

		//设定背景色
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);

		//画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);

		//取随机产生的认证码(4位数字)
		Random random = new Random();
		//生成0－9999的随机数
		int randomNum = random.nextInt(9999);
		//将验证码存在session中
		// session.setAttribute("rand",sRand);
		
		String randStr = String.valueOf(randomNum);

		switch (randStr.length()) {
		case 1:
			randStr = "000" + randStr;
			break;
		case 2:
			randStr = "00" + randStr;
			break;
		case 3:
			randStr = "0" + randStr;
			break;
		default:
			randStr = randStr.substring(0, 4);
			break;
		}
		request.getSession().setAttribute("rand",
		randStr);
		//将认证码显示到图象中
		g.setColor(Color.black);

		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 18));
		String Str = randStr.substring(0, 1);
		g.drawString(Str, 8, 17);

		Str = randStr.substring(1, 2);
		g.drawString(Str, 20, 15);
		Str = randStr.substring(2, 3);
		g.drawString(Str, 35, 18);

		Str = randStr.substring(3, 4);
		g.drawString(Str, 45, 15);

		//          随机产生88个干扰点，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, random.nextInt(5), random.nextInt(5));
		}

		//          图象生效
		g.dispose();

		//          输出图象到页面
		ImageIO.write(image, "JPEG", response.getOutputStream());
		out.clear();
		out = pageContext.pushBody();
	} catch (Exception e) {
		e.printStackTrace();
		out.println(e.toString());
	}
%>



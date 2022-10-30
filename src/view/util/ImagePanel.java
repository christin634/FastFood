package view.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 图片宽度
    private int width;
    // 图片高度
    private int height;
    // 图片
    private BufferedImage image = null;

    public ImagePanel(int width, int height, String path) {
        super();
        // 设置宽高
        this.width = width;
        this.height = height;
        try {
            // 读取图片
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image,0,0,width,height,this);
    }
}


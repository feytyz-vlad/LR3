package ua.com.kisit.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class ClockFrame2 {

    public ClockFrame2() {
        JFrame frame = new JFrame();

        JPanel mainPanel = new RoundedPanel(25);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(420, 140));
        mainPanel.setBackground(new Color(15, 15, 25));

        JLabel clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 38));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(new Color(0, 255, 150));
        clockLabel.setOpaque(false);

        mainPanel.add(clockLabel, BorderLayout.CENTER);

        frame.setSize(440, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(false);
        frame.setIconImage(createIcon());
        frame.setTitle("DIGITAL CLOCK");

        frame.add(mainPanel);

        ClockExt2 clock = new ClockExt2();

        Timer timer = new Timer(10, e -> {
            clock.nextMilliSecond();
            clockLabel.setText(clock.toString());

            float alpha = (System.currentTimeMillis() % 2000 < 1000) ? 0.9f : 1.0f;
            clockLabel.setForeground(new Color(0, 255, 150, (int)(alpha * 255)));
        });

        timer.start();

        makeDraggable(frame, mainPanel);

        frame.setVisible(true);
    }

    private Image createIcon() {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 255, 150));
        g2.fillOval(2, 2, 12, 12);
        g2.setColor(Color.WHITE);
        g2.drawOval(2, 2, 12, 12);
        g2.dispose();
        return img;
    }

    private void makeDraggable(JFrame frame, JPanel panel) {
        final Point[] start = {new Point()};
        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                start[0] = e.getPoint();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = SwingUtilities.convertPoint(panel, e.getPoint(), frame);
                frame.setLocation(frame.getLocation().x + p.x - start[0].x,
                        frame.getLocation().y + p.y - start[0].y);
            }
        });
    }
}

class RoundedPanel extends JPanel {
    private int radius;

    RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(
                0, 0, new Color(20, 20, 40),
                getWidth(), getHeight(), new Color(5, 5, 20)
        );
        g2.setPaint(gp);

        RoundRectangle2D rect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius);
        g2.fill(rect);

        g2.setStroke(new BasicStroke(2.5f));
        g2.setColor(new Color(0, 255, 150, 180));
        g2.draw(rect);

        g2.dispose();
    }
}
package edu.project4.renderers;

import edu.project4.models.FractalImage;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadedRenderer implements Renderer {
    private static final Color COLOR = new Color(0, 200, 200);
    public static final int START = -20;

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long seed,
        int symmetry
    ) {
        for (int num = 0; num < samples; ++num) {
            Point pw = world.getRandomPoint();

            for (short step = START; step < iterPerSample; ++step) {
                Transformation variation = getRandomVariation(variations);
                pw = variation.apply(pw);

                if (step >= 0) {
                    double theta2 = 0.0;
                    for (int s = 0; s < symmetry; ++s) {
                        theta2 += Math.PI * 2.0 / symmetry;

                        Point pwr = rotate(pw, theta2);
                        if (!world.contains(pwr)) {
                            continue;
                        }

                        Pixel pixel = mapRange(world, pwr, canvas);
                        if (pixel == null) {
                            continue;
                        }

                        synchronized (pixel) {
                            if (pixel.getHitCount() == 0) {
                                pixel.setR(COLOR.getRed());
                                pixel.setG(COLOR.getGreen());
                                pixel.setB(COLOR.getBlue());
                            } else {
                                pixel.setR((pixel.getR() + COLOR.getRed()) / 2);
                                pixel.setG((pixel.getG() + COLOR.getGreen()) / 2);
                                pixel.setB((pixel.getB() + COLOR.getBlue()) / 2);
                            }

                            pixel.addHit();
                        }
                    }
                }
            }
        }

        return canvas;
    }

    private Transformation getRandomVariation(List<Transformation> variations) {
        return variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
    }

    private Point rotate(Point point, double angle) {
        double x = point.x() * Math.cos(angle) - point.y() * Math.sin(angle);
        double y = point.x() * Math.sin(angle) + point.y() * Math.cos(angle);

        return new Point(x, y);
    }

    private Pixel mapRange(Rect world, Point pwr, FractalImage canvas) {
        int x = (int) ((pwr.x() - world.x()) * canvas.width() / world.width());
        int y = (int) ((pwr.y() - world.y()) * canvas.height() / world.height());

        if (!canvas.contains(x, y)) {
            return null;
        }

        return canvas.getPixel(x, y);
    }
}

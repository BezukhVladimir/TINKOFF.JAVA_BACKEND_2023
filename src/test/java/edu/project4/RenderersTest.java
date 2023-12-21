package edu.project4;

import edu.project4.models.ImageFormat;
import edu.project4.models.Rect;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import edu.project4.transformations.Linear;
import edu.project4.transformations.Sinusoidal;
import edu.project4.transformations.Spherical;
import edu.project4.transformations.Transformation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public final class RenderersTest {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final Rect WORLD = new Rect(-1.5, -1.5, 3.0, 3.0);
    private static final int SAMPLES = 100_000;
    private static final int ITER_PER_SAMPLE = 200;
    private static final int SYMMETRY = 9;
    private static final double GAMMA = 0.5;
    public static final Path IMAGES_FOLDER = Path.of("src/main/java/edu/project4/images/");

    @Test
    void performanceTest() throws IOException {
        long seed = System.currentTimeMillis();

        List<Transformation> variations = new ArrayList<>();
        variations.add(new Linear());
        variations.add(new Sinusoidal());
        variations.add(new Spherical());

        ImageFormat format = ImageFormat.PNG;
        String fileName = String.format(
            "samples%d_iter%d_symmetry%d_gamma%.2f.%s",
            SAMPLES, ITER_PER_SAMPLE, SYMMETRY, GAMMA, format.name().toLowerCase()
        );
        Path path = IMAGES_FOLDER.resolve(fileName);

        long timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITER_PER_SAMPLE,
            seed,
            SYMMETRY,
            GAMMA,
            1,
            path,
            format
        );
        long nanoEstimatedTime = System.nanoTime();
        long single = nanoEstimatedTime - timeStart;
        System.out.println("SingleThreadedRenderer, seconds:\t" + single / 1e9);

        timeStart = System.nanoTime();
        FractalFlameGenerator.generate(
            WIDTH,
            HEIGHT,
            WORLD,
            variations,
            SAMPLES,
            ITER_PER_SAMPLE,
            seed,
            SYMMETRY,
            GAMMA,
            Runtime.getRuntime().availableProcessors(),
            path,
            format
        );
        nanoEstimatedTime = System.nanoTime();
        long multi = nanoEstimatedTime - timeStart;
        System.out.println("MultiThreadedRenderer,  seconds:\t" + multi / 1e9);
        System.out.println("MultiThreadedRenderer is faster by:\t" + (1.0 * single / multi));
    }
}

package edu.project4;

import edu.project4.models.FractalImage;
import edu.project4.models.ImageFormat;
import edu.project4.models.Rect;
import edu.project4.processors.GammaCorrector;
import edu.project4.processors.ImageProcessor;
import edu.project4.renderers.MultiThreadedRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleThreadedRenderer;
import edu.project4.transformations.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings({"ParameterNumber"})
public class FractalFlameGenerator {
    private FractalFlameGenerator() {
    }

    public static FractalImage generate(
        int width,
        int height,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        long seed,
        int symmetry,
        double gamma,
        int nThreads,
        Path path,
        ImageFormat format
    ) throws IOException {
        if (width <= 0) {
            throw new IllegalArgumentException("Width should be positive");
        }

        if (height <= 0) {
            throw new IllegalArgumentException("Height should be positive");
        }

        if (samples <= 0) {
            throw new IllegalArgumentException("Samples should be positive");
        }

        if (iterPerSample <= 0) {
            throw new IllegalArgumentException("Iter per sample should be positive");
        }

        if (symmetry <= 0) {
            throw new IllegalArgumentException("Symmetry should be positive");
        }

        if (nThreads <= 0) {
            throw new IllegalArgumentException("Number of threads should be positive");
        }

        if (variations.isEmpty()) {
            throw new IllegalArgumentException("Variation list should not be empty");
        }

        if (!path.toString().endsWith("." + format.name().toLowerCase())) {
            throw new IllegalArgumentException("Incorrect file extension");
        }

        var canvas = FractalImage.create(width, height);

        Renderer renderer = nThreads == 1
            ? new SingleThreadedRenderer()
            : new MultiThreadedRenderer(nThreads);

        FractalImage renderedImage = renderer.render(
            canvas,
            world,
            variations,
            samples,
            iterPerSample,
            seed,
            symmetry
        );

        if (gamma != 0.0) {
            ImageProcessor processor = new GammaCorrector(gamma);
            processor.process(renderedImage);
        }

        ImageUtils.save(renderedImage, path, format);

        return renderedImage;
    }
}

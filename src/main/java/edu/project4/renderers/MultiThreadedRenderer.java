package edu.project4.renderers;

import edu.project4.models.FractalImage;
import edu.project4.models.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public class MultiThreadedRenderer implements Renderer {
    private final int nThreads;

    public MultiThreadedRenderer(int nThreads) {
        this.nThreads = Math.min(
            nThreads,
            Runtime.getRuntime().availableProcessors()
        );
    }

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
        var threads = new Thread[nThreads];
        int samplesPerThread = samples / nThreads;

        for (int i = 0; i < nThreads; ++i) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                var renderer = new SingleThreadedRenderer();
                renderer.render(
                    canvas,
                    world,
                    variations,
                    samplesPerThread,
                    iterPerSample,
                    seed + threadIndex,
                    symmetry
                );
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        }

        return canvas;
    }
}

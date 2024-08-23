package tobinio.sweetfruits.util;

/**
 * Created: 23.08.24
 *
 * @author Tobias Frischmann
 */

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.ArrayList;
import java.util.List;

public class VoxelRotation {
    public static VoxelShape rotate(VoxelShape shape, VoxelShapeTransformation transformation) {
        List<VoxelShape> shapes = new ArrayList<>();
        shapes.add(VoxelShapes.empty());
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            double[] adjustedValues = adjustValues(transformation, minX, minY, minZ, maxX, maxY, maxZ);
            shapes.add(VoxelShapes.cuboid(
                    adjustedValues[0], adjustedValues[1],
                    adjustedValues[2], adjustedValues[3],
                    adjustedValues[4], adjustedValues[5]
            ));
        });
        return shapes.stream().reduce(VoxelShapes.empty(), VoxelShapes::union);
    }

    private static double[] adjustValues(VoxelShapeTransformation direction, double minX, double minY, double minZ,
            double maxX, double maxY, double maxZ) {
        return switch (direction) {
            case FLIP_HORIZONTAL -> new double[]{1.0 - maxX, minY, 1.0 - maxZ, 1.0 - minX, maxY, 1.0 - minZ};
            case ROTATE -> new double[]{minZ, minY, 1.0 - maxX, maxZ, maxY, 1.0 - minX};
            case ROTATE_X -> new double[]{minY, minZ, minX, maxY, maxZ, maxX};
        };
    }

    public enum VoxelShapeTransformation {
        FLIP_HORIZONTAL,
        ROTATE,
        ROTATE_X
    }
}
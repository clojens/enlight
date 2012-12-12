package enlight.geom.primitive;

import java.util.Collection;

import mikera.vectorz.Vector3;
import enlight.EnlightError;
import enlight.geom.APrimitive;
import enlight.geom.ASceneObject;
import enlight.geom.IntersectionInfo;

import enlight.geom.APrimitive;

public final class Union extends APrimitive {

	private final boolean finite;
	private final int count;
	private final ASceneObject[] objects;
	
	private Union(ASceneObject[] objects) {
		this.objects=objects;
		count=objects.length;
		
		boolean anyInfinite=false;
		for (int i=0; i<count; i++) {
			if (!objects[i].isFinite()) anyInfinite=true;
		}
		finite=!anyInfinite;
	}
	
	public Union of(Collection<ASceneObject> objects) {
		return new Union(objects.toArray(new ASceneObject[objects.size()]));
	}
	
	@Override
	public boolean isFinite() {
		return finite;
	}

	@Override
	public void getSupport(Vector3 normal, IntersectionInfo resultOut) {
		if (!isFinite()) throw new EnlightError("Can't get support of union of infinite objects!");
		throw new UnsupportedOperationException("Not yet implemented!");
	}

	@Override
	public void getIntersection(Vector3 start, Vector3 direction,
			double startDist, IntersectionInfo result) {
		// TODO Auto-generated method stub
		
	}

}

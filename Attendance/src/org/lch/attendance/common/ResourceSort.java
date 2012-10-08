package org.lch.attendance.common;

import java.util.Comparator;

import org.lch.attendance.domain.Resource;

public class ResourceSort implements Comparator<Resource> {

	public final static int UP = 1;

	public final static int DOWM = -1;

	private int state;

	public ResourceSort(int state) {
		this.state = state;
	}

	public ResourceSort() {

	}

	public int compare(Resource o1, Resource o2) {
		if (state == ResourceSort.DOWM) {
			return sortDown(o1, o2);
		}
		return sortUp(o1, o2);
	}

	private int sortUp(Resource o1, Resource o2) {
		if (o1.getId().compareTo(o2.getId()) < 0) {
			return -1;
		} else if (o1.getId().compareTo(o2.getId()) > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	private int sortDown(Resource o1, Resource o2) {
		if (o1.getId().compareTo(o2.getId()) > 0) {
			return -1;
		} else if (o1.getId().compareTo(o2.getId()) < 0) {
			return 1;
		} else {
			return 0;
		}
	}
}

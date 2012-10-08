package org.lch.attendance.service;

import org.lch.attendance.domain.Clas;

public interface ClasService extends GenericService<Clas> {

	Clas doFindByName(String className);

}

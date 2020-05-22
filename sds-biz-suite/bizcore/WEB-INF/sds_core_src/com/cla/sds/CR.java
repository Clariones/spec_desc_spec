package com.cla.sds;

import com.terapico.changerequest.BaseCrConst;

public interface CR extends BaseCrConst {

	interface UPDATE_PROFILE {
		String NAME = "UPDATE_PROFILE";
		String TITLE = "更新个人信息";
		interface SCENE_UPDATE_PROFILE {
			String NAME = "updateProfile";
			String TITLE = "更新个人信息";
			boolean CAN_SKIP = false;
			interface GROUP_UPDATE_PROFILE {
			    String NAME = "upUpdateProfile_updateProfile";
                String FIELD_NAME = "name";
                String FIELD_AVANTAR = "avantar";
			}
		}
        String FIELD_NAME_IN_UPDATE_PROFILE_OF_UPDATE_PROFILE = "upUpdateProfile_updateProfile_name";
        String FIELD_AVANTAR_IN_UPDATE_PROFILE_OF_UPDATE_PROFILE = "upUpdateProfile_updateProfile_avantar";
	}
	
}

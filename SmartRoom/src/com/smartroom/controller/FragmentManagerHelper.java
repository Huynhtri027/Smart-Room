package com.smartroom.controller;

import android.app.Fragment;
import android.app.FragmentTransaction;

import com.smartroom.R;
import com.smartroom.utilities.Utils;
import com.smartroom.view.NoNetwork;

/**
 * @author TheAppExpert-Fillipo
 * 
 */
public class FragmentManagerHelper {

	public static Fragment currentFragment = null;
	public static Fragment currentLoggedUserFragment = null;

	public static Fragment getCurrentLoggedUserFragment() {
		return currentLoggedUserFragment;
	}

	public static void setCurrentLoggedUserFragment(
			Fragment currentLoggedUserFragment) {
		FragmentManagerHelper.currentLoggedUserFragment = currentLoggedUserFragment;
	}

	public static String fragmentType = null;
	private static FragmentTransaction transaction = null;
	public static boolean userStatus = false;

	public static boolean isUserStatus() {
		return userStatus;
	}

	public static void setUserStatus(boolean userStatus) {
		FragmentManagerHelper.userStatus = userStatus;
	}

	public static String getFragmentType() {
		return fragmentType;
	}

	public static void setFragmentType(String fragmentType) {
		FragmentManagerHelper.fragmentType = fragmentType;
	}

	public static void setFragmentTransaction(FragmentTransaction ft) {
		transaction = ft;
	}

	public static Fragment getCurrentFragment() {
		return currentFragment;
	}

	public static void setCurrentFragment(Fragment currentFragment) {
		FragmentManagerHelper.currentFragment = currentFragment;
	}

	public static void addFragment(Fragment newFragment) {
		transaction.setCustomAnimations(R.animator.bounce_in_down,
				R.animator.slide_out_down).add(R.id.content_frame, newFragment);
		transaction.commit();
	}

	public static void replaceFragment(Fragment oldFragment) {

		boolean netCheck = Utils.isNetworkAvailable(Utils.getCurrentActivity());
		if (netCheck) {
			if (FragmentManagerHelper.isUserStatus()) {
				transaction
						.replace(R.id.content_frame_logged_user, oldFragment);
				transaction
						.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				transaction.commit();
			} else {
				transaction.setCustomAnimations(R.animator.bounce_in_down,
						R.animator.slide_out_down);
				transaction.replace(R.id.content_frame, oldFragment);
				transaction
						.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				transaction.commit();
			}
		} else {

			if (FragmentManagerHelper.isUserStatus()) {
				transaction.setCustomAnimations(R.animator.bounce_in_down,
						R.animator.slide_out_down);
				transaction.replace(R.id.content_frame_logged_user,
						new NoNetwork());
				transaction
						.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				transaction.commit();
			} else {
				transaction.setCustomAnimations(R.animator.bounce_in_down,
						R.animator.slide_out_down);
				transaction.replace(R.id.content_frame, new NoNetwork());
				transaction
						.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				transaction.commit();
			}
		}

	}

	public static void replaceFragmentWithoutAnimation(Fragment oldFragment) {

		boolean netCheck = Utils.isNetworkAvailable(Utils.getCurrentActivity());
		if (netCheck) {
			if (FragmentManagerHelper.isUserStatus()) {
				transaction
						.replace(R.id.content_frame_logged_user, oldFragment);
				transaction
						.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				transaction.commit();
			} else {
				transaction.replace(R.id.content_frame, oldFragment);
				transaction
						.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				transaction.commit();
			}
		} else {
			transaction.setCustomAnimations(R.animator.bounce_in_down,
					R.animator.slide_out_down);
			transaction.replace(R.id.content_frame, new NoNetwork());
			transaction
					.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			transaction.commit();
		}

	}

	public static void removeFragment(Fragment oldFragment) {
		transaction.remove(oldFragment).commit();
		currentFragment = null;
	}
}

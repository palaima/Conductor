package com.example.myapplication.controller;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.ChangeHandlerFrameLayout;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.myapplication.R;
import com.example.myapplication.controller.base.BaseController;

public class RootController extends BaseController {

  private Router childRouter;

  @Override
  protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    return inflater.inflate(R.layout.screen_root, container, false);
  }

  @Override
  protected void onViewBound(@NonNull View view) {
    super.onViewBound(view);
    ChangeHandlerFrameLayout changeHandlerFrameLayout = ((ChangeHandlerFrameLayout) view.findViewById(R.id.controller_container));
    childRouter = getChildRouter(changeHandlerFrameLayout, null).setPopsLastView(true);
    if (!childRouter.hasRootController()) {
      childRouter.setRoot(RouterTransaction.with(new PagerController()));
    }
  }

  @Override
  public boolean handleBack() {
    if (childRouter.getBackstackSize() == 1) {
      return getActivity().moveTaskToBack(true);
    } else {
      return super.handleBack();
    }
  }
}

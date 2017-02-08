package com.example.myapplication.controller;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.support.ControllerPagerAdapter;
import com.example.myapplication.R;
import com.example.myapplication.controller.base.BaseController;

import butterknife.BindView;

public class PagerController extends BaseController {

    private int[] PAGE_COLORS = new int[]{R.color.green_300, R.color.cyan_300, R.color.deep_purple_300, R.color.lime_300, R.color.red_300};

    @BindView(R.id.main_pager_tab_layout) TabLayout tabLayout;
    @BindView(R.id.main_pager_view_pager) ViewPager viewPager;

    private final ControllerPagerAdapter pagerAdapter;

    public PagerController() {

        pagerAdapter = new ControllerPagerAdapter(this, false) {
            @Override
            public int getCount() {
                return 2;
            }

            @NonNull
            @Override
            public Controller getItem(int position) {
                final Controller controller;

                switch (position) {
                    case 0:
                        controller = new PagerChild1Controller("Pager child 1", PAGE_COLORS[position], true);
                        break;
                    case 1:
                        controller = new PagerChild2Controller("Pager child 2", PAGE_COLORS[position], true);
                        break;
                    default:
                        throw new IllegalStateException("");
                }

                return controller;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Page " + position;
            }
        };

        /*pagerAdapter = new RouterPagerAdapter(this) {
            @Override
            public void configureRouter(@NonNull Router router, int position) {
                if (!router.hasRootController()) {
                    Controller page = new ChildController(String.format(Locale.getDefault(), "Child #%d (Swipe to see more)", position), PAGE_COLORS[position], true);
                    router.setRoot(RouterTransaction.with(page));
                }
            }

            @Override
            public int getCount() {
                return PAGE_COLORS.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Page " + position;
            }
        };*/
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        viewPager.setAdapter(null);
        super.onDestroyView(view);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_main_pager, container, false);
    }

    @Override
    protected String getTitle() {
        return "ViewPager Demo";
    }

}

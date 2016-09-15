package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class FragmentTransitionCompat21 {

    public interface ViewRetriever {
        View getView();
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.1 */
    static class AnonymousClass1 extends EpicenterCallback {
        final /* synthetic */ Rect val$epicenter;

        AnonymousClass1(Rect rect) {
            this.val$epicenter = rect;
        }

        public Rect onGetEpicenter(Transition transition) {
            return this.val$epicenter;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.2 */
    static class AnonymousClass2 implements OnPreDrawListener {
        final /* synthetic */ View val$container;
        final /* synthetic */ Transition val$enterTransition;
        final /* synthetic */ ArrayList val$enteringViews;
        final /* synthetic */ ViewRetriever val$inFragment;
        final /* synthetic */ Map val$nameOverrides;
        final /* synthetic */ View val$nonExistentView;
        final /* synthetic */ Map val$renamedViews;

        AnonymousClass2(View view, Transition transition, View view2, ViewRetriever viewRetriever, Map map, Map map2, ArrayList arrayList) {
            this.val$container = view;
            this.val$enterTransition = transition;
            this.val$nonExistentView = view2;
            this.val$inFragment = viewRetriever;
            this.val$nameOverrides = map;
            this.val$renamedViews = map2;
            this.val$enteringViews = arrayList;
        }

        public boolean onPreDraw() {
            this.val$container.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.val$enterTransition != null) {
                this.val$enterTransition.removeTarget(this.val$nonExistentView);
            }
            View fragmentView = this.val$inFragment.getView();
            if (fragmentView != null) {
                if (!this.val$nameOverrides.isEmpty()) {
                    FragmentTransitionCompat21.findNamedViews(this.val$renamedViews, fragmentView);
                    this.val$renamedViews.keySet().retainAll(this.val$nameOverrides.values());
                    for (Entry<String, String> entry : this.val$nameOverrides.entrySet()) {
                        View view = (View) this.val$renamedViews.get((String) entry.getValue());
                        if (view != null) {
                            view.setTransitionName((String) entry.getKey());
                        }
                    }
                }
                if (this.val$enterTransition != null) {
                    FragmentTransitionCompat21.captureTransitioningViews(this.val$enteringViews, fragmentView);
                    this.val$enteringViews.removeAll(this.val$renamedViews.values());
                    this.val$enteringViews.add(this.val$nonExistentView);
                    FragmentTransitionCompat21.addTargets(this.val$enterTransition, this.val$enteringViews);
                }
            }
            return true;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.3 */
    static class AnonymousClass3 extends EpicenterCallback {
        private Rect mEpicenter;
        final /* synthetic */ EpicenterView val$epicenterView;

        AnonymousClass3(EpicenterView epicenterView) {
            this.val$epicenterView = epicenterView;
        }

        public Rect onGetEpicenter(Transition transition) {
            if (this.mEpicenter == null && this.val$epicenterView.epicenter != null) {
                this.mEpicenter = FragmentTransitionCompat21.getBoundsOnScreen(this.val$epicenterView.epicenter);
            }
            return this.mEpicenter;
        }
    }

    /* renamed from: android.support.v4.app.FragmentTransitionCompat21.4 */
    static class AnonymousClass4 implements OnPreDrawListener {
        final /* synthetic */ Transition val$enterTransition;
        final /* synthetic */ ArrayList val$enteringViews;
        final /* synthetic */ Transition val$exitTransition;
        final /* synthetic */ ArrayList val$exitingViews;
        final /* synthetic */ ArrayList val$hiddenViews;
        final /* synthetic */ View val$nonExistentView;
        final /* synthetic */ Transition val$overallTransition;
        final /* synthetic */ Map val$renamedViews;
        final /* synthetic */ View val$sceneRoot;
        final /* synthetic */ ArrayList val$sharedElementTargets;
        final /* synthetic */ Transition val$sharedElementTransition;

        AnonymousClass4(View view, Transition transition, ArrayList arrayList, Transition transition2, ArrayList arrayList2, Transition transition3, ArrayList arrayList3, Map map, ArrayList arrayList4, Transition transition4, View view2) {
            this.val$sceneRoot = view;
            this.val$enterTransition = transition;
            this.val$enteringViews = arrayList;
            this.val$exitTransition = transition2;
            this.val$exitingViews = arrayList2;
            this.val$sharedElementTransition = transition3;
            this.val$sharedElementTargets = arrayList3;
            this.val$renamedViews = map;
            this.val$hiddenViews = arrayList4;
            this.val$overallTransition = transition4;
            this.val$nonExistentView = view2;
        }

        public boolean onPreDraw() {
            this.val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.val$enterTransition != null) {
                FragmentTransitionCompat21.removeTargets(this.val$enterTransition, this.val$enteringViews);
            }
            if (this.val$exitTransition != null) {
                FragmentTransitionCompat21.removeTargets(this.val$exitTransition, this.val$exitingViews);
            }
            if (this.val$sharedElementTransition != null) {
                FragmentTransitionCompat21.removeTargets(this.val$sharedElementTransition, this.val$sharedElementTargets);
            }
            for (Entry<String, View> entry : this.val$renamedViews.entrySet()) {
                ((View) entry.getValue()).setTransitionName((String) entry.getKey());
            }
            int numViews = this.val$hiddenViews.size();
            for (int i = 0; i < numViews; i++) {
                this.val$overallTransition.excludeTarget((View) this.val$hiddenViews.get(i), false);
            }
            this.val$overallTransition.excludeTarget(this.val$nonExistentView, false);
            return true;
        }
    }

    public static class EpicenterView {
        public View epicenter;
    }

    FragmentTransitionCompat21() {
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    public static Object cloneTransition(Object transition) {
        if (transition != null) {
            return ((Transition) transition).clone();
        }
        return transition;
    }

    public static Object captureExitingViews(Object exitTransition, View root, ArrayList<View> viewList, Map<String, View> namedViews, View nonExistentView) {
        if (exitTransition == null) {
            return exitTransition;
        }
        captureTransitioningViews(viewList, root);
        if (namedViews != null) {
            viewList.removeAll(namedViews.values());
        }
        if (viewList.isEmpty()) {
            return null;
        }
        viewList.add(nonExistentView);
        addTargets((Transition) exitTransition, viewList);
        return exitTransition;
    }

    public static void excludeTarget(Object transitionObject, View view, boolean exclude) {
        ((Transition) transitionObject).excludeTarget(view, exclude);
    }

    public static void beginDelayedTransition(ViewGroup sceneRoot, Object transitionObject) {
        TransitionManager.beginDelayedTransition(sceneRoot, (Transition) transitionObject);
    }

    public static void setEpicenter(Object transitionObject, View view) {
        ((Transition) transitionObject).setEpicenterCallback(new AnonymousClass1(getBoundsOnScreen(view)));
    }

    public static Object wrapSharedElementTransition(Object transitionObj) {
        if (transitionObj == null) {
            return null;
        }
        Transition transition = (Transition) transitionObj;
        if (transition == null) {
            return null;
        }
        Object transitionSet = new TransitionSet();
        transitionSet.addTransition(transition);
        return transitionSet;
    }

    public static void addTransitionTargets(Object enterTransitionObject, Object sharedElementTransitionObject, View container, ViewRetriever inFragment, View nonExistentView, EpicenterView epicenterView, Map<String, String> nameOverrides, ArrayList<View> enteringViews, Map<String, View> namedViews, Map<String, View> renamedViews, ArrayList<View> sharedElementTargets) {
        if (enterTransitionObject != null || sharedElementTransitionObject != null) {
            Transition enterTransition = (Transition) enterTransitionObject;
            if (enterTransition != null) {
                enterTransition.addTarget(nonExistentView);
            }
            if (sharedElementTransitionObject != null) {
                setSharedElementTargets(sharedElementTransitionObject, nonExistentView, namedViews, sharedElementTargets);
            }
            if (inFragment != null) {
                container.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass2(container, enterTransition, nonExistentView, inFragment, nameOverrides, renamedViews, enteringViews));
            }
            setSharedElementEpicenter(enterTransition, epicenterView);
        }
    }

    public static Object mergeTransitions(Object enterTransitionObject, Object exitTransitionObject, Object sharedElementTransitionObject, boolean allowOverlap) {
        boolean overlap = true;
        Transition enterTransition = (Transition) enterTransitionObject;
        Transition exitTransition = (Transition) exitTransitionObject;
        Transition sharedElementTransition = (Transition) sharedElementTransitionObject;
        if (!(enterTransition == null || exitTransition == null)) {
            overlap = allowOverlap;
        }
        if (overlap) {
            Transition transitionSet = new TransitionSet();
            if (enterTransition != null) {
                transitionSet.addTransition(enterTransition);
            }
            if (exitTransition != null) {
                transitionSet.addTransition(exitTransition);
            }
            if (sharedElementTransition != null) {
                transitionSet.addTransition(sharedElementTransition);
            }
            return transitionSet;
        }
        Transition staggered = null;
        if (exitTransition != null && enterTransition != null) {
            staggered = new TransitionSet().addTransition(exitTransition).addTransition(enterTransition).setOrdering(1);
        } else if (exitTransition != null) {
            staggered = exitTransition;
        } else if (enterTransition != null) {
            staggered = enterTransition;
        }
        if (sharedElementTransition == null) {
            return staggered;
        }
        Transition together = new TransitionSet();
        if (staggered != null) {
            together.addTransition(staggered);
        }
        together.addTransition(sharedElementTransition);
        return together;
    }

    public static void setSharedElementTargets(Object transitionObj, View nonExistentView, Map<String, View> namedViews, ArrayList<View> sharedElementTargets) {
        TransitionSet transition = (TransitionSet) transitionObj;
        sharedElementTargets.clear();
        sharedElementTargets.addAll(namedViews.values());
        List<View> views = transition.getTargets();
        views.clear();
        int count = sharedElementTargets.size();
        for (int i = 0; i < count; i++) {
            bfsAddViewChildren(views, (View) sharedElementTargets.get(i));
        }
        sharedElementTargets.add(nonExistentView);
        addTargets(transition, sharedElementTargets);
    }

    private static void bfsAddViewChildren(List<View> views, View startView) {
        int startIndex = views.size();
        if (!containedBeforeIndex(views, startView, startIndex)) {
            views.add(startView);
            for (int index = startIndex; index < views.size(); index++) {
                View view = (View) views.get(index);
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    int childCount = viewGroup.getChildCount();
                    for (int childIndex = 0; childIndex < childCount; childIndex++) {
                        View child = viewGroup.getChildAt(childIndex);
                        if (!containedBeforeIndex(views, child, startIndex)) {
                            views.add(child);
                        }
                    }
                }
            }
        }
    }

    private static boolean containedBeforeIndex(List<View> views, View view, int maxIndex) {
        for (int i = 0; i < maxIndex; i++) {
            if (views.get(i) == view) {
                return true;
            }
        }
        return false;
    }

    private static void setSharedElementEpicenter(Transition transition, EpicenterView epicenterView) {
        if (transition != null) {
            transition.setEpicenterCallback(new AnonymousClass3(epicenterView));
        }
    }

    private static Rect getBoundsOnScreen(View view) {
        Rect epicenter = new Rect();
        int[] loc = new int[2];
        view.getLocationOnScreen(loc);
        epicenter.set(loc[0], loc[1], loc[0] + view.getWidth(), loc[1] + view.getHeight());
        return epicenter;
    }

    private static void captureTransitioningViews(ArrayList<View> transitioningViews, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                transitioningViews.add(viewGroup);
                return;
            }
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                captureTransitioningViews(transitioningViews, viewGroup.getChildAt(i));
            }
            return;
        }
        transitioningViews.add(view);
    }

    public static void findNamedViews(Map<String, View> namedViews, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                namedViews.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int count = viewGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    findNamedViews(namedViews, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public static void cleanupTransitions(View sceneRoot, View nonExistentView, Object enterTransitionObject, ArrayList<View> enteringViews, Object exitTransitionObject, ArrayList<View> exitingViews, Object sharedElementTransitionObject, ArrayList<View> sharedElementTargets, Object overallTransitionObject, ArrayList<View> hiddenViews, Map<String, View> renamedViews) {
        Transition enterTransition = (Transition) enterTransitionObject;
        Transition exitTransition = (Transition) exitTransitionObject;
        Transition sharedElementTransition = (Transition) sharedElementTransitionObject;
        Transition overallTransition = (Transition) overallTransitionObject;
        if (overallTransition != null) {
            sceneRoot.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass4(sceneRoot, enterTransition, enteringViews, exitTransition, exitingViews, sharedElementTransition, sharedElementTargets, renamedViews, hiddenViews, overallTransition, nonExistentView));
        }
    }

    public static void removeTargets(Object transitionObject, ArrayList<View> views) {
        Transition transition = (Transition) transitionObject;
        int i;
        if (transition instanceof TransitionSet) {
            TransitionSet set = (TransitionSet) transition;
            int numTransitions = set.getTransitionCount();
            for (i = 0; i < numTransitions; i++) {
                removeTargets(set.getTransitionAt(i), views);
            }
        } else if (!hasSimpleTarget(transition)) {
            List<View> targets = transition.getTargets();
            if (targets != null && targets.size() == views.size() && targets.containsAll(views)) {
                for (i = views.size() - 1; i >= 0; i--) {
                    transition.removeTarget((View) views.get(i));
                }
            }
        }
    }

    public static void addTargets(Object transitionObject, ArrayList<View> views) {
        Transition transition = (Transition) transitionObject;
        int i;
        if (transition instanceof TransitionSet) {
            TransitionSet set = (TransitionSet) transition;
            int numTransitions = set.getTransitionCount();
            for (i = 0; i < numTransitions; i++) {
                addTargets(set.getTransitionAt(i), views);
            }
        } else if (!hasSimpleTarget(transition) && isNullOrEmpty(transition.getTargets())) {
            int numViews = views.size();
            for (i = 0; i < numViews; i++) {
                transition.addTarget((View) views.get(i));
            }
        }
    }

    private static boolean hasSimpleTarget(Transition transition) {
        return (isNullOrEmpty(transition.getTargetIds()) && isNullOrEmpty(transition.getTargetNames()) && isNullOrEmpty(transition.getTargetTypes())) ? false : true;
    }

    private static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }
}

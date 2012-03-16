/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.view;

import android.os.Handler;

/**
 * A display lists records a series of graphics related operation and can replay
 * them later. Display lists are usually built by recording operations on a
 * {@link android.graphics.Canvas}. Replaying the operations from a display list
 * avoids executing views drawing code on every frame, and is thus much more
 * efficient.
 *
 * @hide 
 */
public abstract class DisplayList {
    /**
     * Flag used when calling
     * {@link HardwareCanvas#drawDisplayList(DisplayList, int, int, android.graphics.Rect, int)}.
     * When this flag is set, draw operations lying outside of the bounds of the
     * display list will be culled early. It is recommeneded to always set this
     * flag.
     */
    public static final int FLAG_CLIP_CHILDREN = 0x1;

    /**
     * Starts recording the display list. All operations performed on the
     * returned canvas are recorded and stored in this display list.
     * 
     * @return A canvas to record drawing operations.
     */
    public abstract HardwareCanvas start();

    /**
     * Ends the recording for this display list. A display list cannot be
     * replayed if recording is not finished. 
     */
    public abstract void end();

    /**
     * Invalidates the display list, indicating that it should be repopulated
     * with new drawing commands prior to being used again. Calling this method
     * causes calls to {@link #isValid()} to return <code>false</code>.
     */
    public abstract void invalidate();

    /**
     * Returns whether the display list is currently usable. If this returns false,
     * the display list should be re-recorded prior to replaying it.
     *
     * @return boolean true if the display list is able to be replayed, false otherwise.
     */
    public abstract boolean isValid();

    /**
     * Return the amount of memory used by this display list.
     * 
     * @return The size of this display list in bytes
     */
    public abstract int getSize();

    ///////////////////////////////////////////////////////////////////////////
    // DisplayList Property Setters
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Set the caching property on the DisplayList, which indicates whether the DisplayList
     * holds a layer. Layer DisplayLists should avoid creating an alpha layer, since alpha is
     * handled in the drawLayer operation directly (and more efficiently).
     *
     * @param caching true if the DisplayList represents a hardware layer, false otherwise.
     */
    public abstract void setCaching(boolean caching);

    /**
     * Set whether the DisplayList should clip itself to its bounds. This property is controlled by
     * the view's parent.
     *
     * @param clipChildren true if the DisplayList should clip to its bounds
     */
    public abstract void setClipChildren(boolean clipChildren);

    /**
     * Set the application scale on the DisplayList. This scale is incurred by applications that
     * are auto-scaled for compatibility reasons. By default, the value is 1 (unscaled).
     *
     * @param scale The scaling factor
     */
    public abstract void setApplicationScale(float scale);

    /**
     * Sets the alpha value for the DisplayList
     *
     * @param alpha The translucency of the DisplayList
     * @see View#setAlpha(float)
     */
    public abstract void setAlpha(float alpha);

    /**
     * Sets the translationX value for the DisplayList
     *
     * @param translationX The translationX value of the DisplayList
     * @see View#setTranslationX(float)
     */
    public abstract void setTranslationX(float translationX);

    /**
     * Sets the translationY value for the DisplayList
     *
     * @param translationY The translationY value of the DisplayList
     * @see View#setTranslationY(float)
     */
    public abstract void setTranslationY(float translationY);

    /**
     * Sets the rotation value for the DisplayList
     *
     * @param rotation The rotation value of the DisplayList
     * @see View#setRotation(float)
     */
    public abstract void setRotation(float rotation);

    /**
     * Sets the rotationX value for the DisplayList
     *
     * @param rotationX The rotationX value of the DisplayList
     * @see View#setRotationX(float)
     */
    public abstract void setRotationX(float rotationX);

    /**
     * Sets the rotationY value for the DisplayList
     *
     * @param rotationY The rotationY value of the DisplayList
     * @see View#setRotationY(float)
     */
    public abstract void setRotationY(float rotationY);

    /**
     * Sets the scaleX value for the DisplayList
     *
     * @param scaleX The scaleX value of the DisplayList
     * @see View#setScaleX(float)
     */
    public abstract void setScaleX(float scaleX);

    /**
     * Sets the scaleY value for the DisplayList
     *
     * @param scaleY The scaleY value of the DisplayList
     * @see View#setScaleY(float)
     */
    public abstract void setScaleY(float scaleY);

    /**
     * Sets all of the transform-related values of the View onto the DisplayList
     *
     * @param alpha The alpha value of the DisplayList
     * @param translationX The translationX value of the DisplayList
     * @param translationY The translationY value of the DisplayList
     * @param rotation The rotation value of the DisplayList
     * @param rotationX The rotationX value of the DisplayList
     * @param rotationY The rotationY value of the DisplayList
     * @param scaleX The scaleX value of the DisplayList
     * @param scaleY The scaleY value of the DisplayList
     */
    public abstract void setTransformationInfo(float alpha, float translationX, float translationY,
            float rotation, float rotationX, float rotationY, float scaleX, float scaleY);

    /**
     * Sets the pivotX value for the DisplayList
     *
     * @param pivotX The pivotX value of the DisplayList
     * @see View#setPivotX(float)
     */
    public abstract void setPivotX(float pivotX);

    /**
     * Sets the pivotY value for the DisplayList
     *
     * @param pivotY The pivotY value of the DisplayList
     * @see View#setPivotY(float)
     */
    public abstract void setPivotY(float pivotY);

    /**
     * Sets the camera distance for the DisplayList
     *
     * @param distance The distance in z of the camera of the DisplayList
     * @see View#setCameraDistance(float)
     */
    public abstract void setCameraDistance(float distance);

    /**
     * Sets the left value for the DisplayList
     *
     * @param left The left value of the DisplayList
     * @see View#setLeft(int)
     */
    public abstract void setLeft(int left);

    /**
     * Sets the top value for the DisplayList
     *
     * @param top The top value of the DisplayList
     * @see View#setTop(int)
     */
    public abstract void setTop(int top);

    /**
     * Sets the right value for the DisplayList
     *
     * @param right The right value of the DisplayList
     * @see View#setRight(int)
     */
    public abstract void setRight(int right);

    /**
     * Sets the bottom value for the DisplayList
     *
     * @param bottom The bottom value of the DisplayList
     * @see View#setBottom(int)
     */
    public abstract void setBottom(int bottom);

    /**
     * Sets the left and top values for the DisplayList
     *
     * @param left The left value of the DisplayList
     * @param top The top value of the DisplayList
     * @see View#setLeft(int)
     * @see View#setTop(int)
     */
    public abstract void setLeftTop(int left, int top);

    /**
     * Sets the left and top values for the DisplayList
     *
     * @param left The left value of the DisplayList
     * @param top The top value of the DisplayList
     * @see View#setLeft(int)
     * @see View#setTop(int)
     */
    public abstract void setLeftTopRightBottom(int left, int top, int right, int bottom);

    /**
     * Offsets the left and right values for the DisplayList
     *
     * @param offset The amount that the left and right values of the DisplayList are offset
     * @see View#offsetLeftAndRight(int)
     */
    public abstract void offsetLeftRight(int offset);

    /**
     * Offsets the top and bottom values for the DisplayList
     *
     * @param offset The amount that the top and bottom values of the DisplayList are offset
     * @see View#offsetTopAndBottom(int)
     */
    public abstract void offsetTopBottom(int offset);
}

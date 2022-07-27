package com.smb.core.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.MotionEvent.*
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE
import androidx.recyclerview.widget.ItemTouchHelper.Callback
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.smb.core.presentation.adapters.ButtonsState.*
import kotlin.math.max
import kotlin.math.min


const val buttonWidth = 150f

internal enum class ButtonsState {
    GONE, LEFT_VISIBLE, RIGHT_VISIBLE
}

abstract class SwipeControllerAlt(buttonsActions: SwipeControllerActions?, val context: Context) :
    Callback() {
    private var swipeBack = false
    private var buttonShowedState = GONE
    private var buttonInstance: RectF? = null
    private var currentItemViewHolder: RecyclerView.ViewHolder? = null
    private var buttonsActions: SwipeControllerActions? = null

    init {
        this.buttonsActions = buttonsActions
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(0, LEFT or RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = buttonShowedState != GONE
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        var buttonPosition = dX
        if (actionState == ACTION_STATE_SWIPE) {
            if (buttonShowedState != GONE) {
                buttonPosition = when (buttonShowedState) {
                    LEFT_VISIBLE -> max(dX, buttonWidth)
                    RIGHT_VISIBLE -> min(dX, -buttonWidth)
                    else -> 0f
                }
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    buttonPosition,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            } else {
                setTouchListener(
                    c,
                    recyclerView,
                    viewHolder,
                    buttonPosition,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        if (buttonShowedState == GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
        currentItemViewHolder = viewHolder
        currentItemViewHolder?.let { drawButtons(c, it) }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            swipeBack = event.action == ACTION_CANCEL || event.action == ACTION_UP
            if (swipeBack) {
                if (dX < -buttonWidth) buttonShowedState =
                    RIGHT_VISIBLE else if (dX > buttonWidth) buttonShowedState =
                    LEFT_VISIBLE
                if (buttonShowedState != GONE) {
                    setTouchDownListener(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                    setItemsClickable(recyclerView, false)
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchDownListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == ACTION_DOWN) {
                setTouchUpListener(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchUpListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == ACTION_UP) {
                super@SwipeControllerAlt.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    0f,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                recyclerView.setOnTouchListener { _, _ -> false }
                setItemsClickable(recyclerView, true)
                swipeBack = false
                if (buttonsActions != null && buttonInstance != null && buttonInstance!!.contains(
                        event.x,
                        event.y
                    )
                ) {
                    if (buttonShowedState == LEFT_VISIBLE) {
                        buttonsActions!!.onLeftClicked(viewHolder.adapterPosition)
                    } else if (buttonShowedState == RIGHT_VISIBLE) {
                        buttonsActions!!.onRightClicked(viewHolder.adapterPosition)
                    }
                }
                buttonShowedState = GONE
                currentItemViewHolder = null
            }
            false
        }
    }

    private fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {
        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithoutPadding = buttonWidth - 20
        val itemView: View = viewHolder.itemView
        val p = Paint()

        val leftButton = RectF(
            itemView.left.toFloat(),
            itemView.top.toFloat(),
            itemView.left.toFloat() + buttonWidthWithoutPadding,
            itemView.bottom.toFloat()
        )

        drawButton(c, leftButton, p, Color.BLUE, R.drawable.ic_edit)

        val rightButton = RectF(
            itemView.right.toFloat() - buttonWidthWithoutPadding,
            itemView.top.toFloat(),
            itemView.right.toFloat(),
            itemView.bottom.toFloat()
        )

        drawButton(c, rightButton, p, Color.RED, R.drawable.ic_delete)

        buttonInstance = when (buttonShowedState) {
            LEFT_VISIBLE -> leftButton
            RIGHT_VISIBLE -> rightButton
            else -> null
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun drawButton(c: Canvas, button: RectF, p: Paint, color: Int, icon: Int) {
        val wrappedDrawable = AppCompatResources.getDrawable(context, icon)
        val unwrappedDrawable = DrawableCompat.wrap(wrappedDrawable!!)
        DrawableCompat.setTint(unwrappedDrawable, Color.WHITE)

        p.color = color
        c.apply {
            drawRoundRect(button, 0f, 0f, p)
            drawBitmap(
                unwrappedDrawable.toBitmap(),
                button.centerX() - (unwrappedDrawable.toBitmap().width / 2),
                button.centerY() - (unwrappedDrawable.toBitmap().height / 2),
                null
            )
        }

    }
}
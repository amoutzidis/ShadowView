package gr.amoutzidis.shadowview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View

class ShadowView: View {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.shadowViewStyle
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.ShadowView, defStyleAttr,
            R.style.ShadowViewStyle
        )

        val colors = parseColors(
            a.getColor(R.styleable.ShadowView_startColor, 0),
            a.getColor(R.styleable.ShadowView_centerColor, 0),
            a.getColor(R.styleable.ShadowView_endColor, 0)
        )

        val gradientOrientation = parseStartFrom(
            a.getInt(
                R.styleable.ShadowView_startFrom,
                0
            )
        )


        val gradientDrawable = GradientDrawable(gradientOrientation, colors)
        background = gradientDrawable
    }


    private fun parseColors(startColor: Int, centerColor: Int, endColor: Int): IntArray{
        val colors = mutableListOf<Int>()

        if(startColor != 0)
            colors.add(startColor)

        if(centerColor != 0)
            colors.add(centerColor)

        if(endColor != 0)
            colors.add(endColor)

        return colors.toIntArray()
    }


    private fun parseStartFrom(startFrom: Int): GradientDrawable.Orientation{
        return when(startFrom){
            0 -> GradientDrawable.Orientation.TOP_BOTTOM
            1 -> GradientDrawable.Orientation.RIGHT_LEFT
            2 -> GradientDrawable.Orientation.BOTTOM_TOP
            3 -> GradientDrawable.Orientation.LEFT_RIGHT
            4 -> {
                if(LayoutDirectionHelper.isRTL(context))
                    GradientDrawable.Orientation.RIGHT_LEFT
                else
                    GradientDrawable.Orientation.LEFT_RIGHT
            }
            5 -> {
                if(LayoutDirectionHelper.isRTL(context))
                    GradientDrawable.Orientation.LEFT_RIGHT
                else
                    GradientDrawable.Orientation.RIGHT_LEFT
            }

            else-> throw Exception("No valid orientation")
        }
    }



}
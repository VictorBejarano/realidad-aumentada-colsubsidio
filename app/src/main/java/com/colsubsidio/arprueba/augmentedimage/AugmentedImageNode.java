package com.colsubsidio.arprueba.augmentedimage;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.colsubsidio.arprueba.R;
import com.google.ar.core.AugmentedImage;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.ExternalTexture;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.rendering.ViewSizer;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings({"AndroidApiChecker"})
public class AugmentedImageNode extends AnchorNode {
    private static final String TAG = "AugmentedImageNode";

    public MediaPlayer mediaPlayer;
    private AugmentedImage image;
    private ModelRenderable videoRenderable;
    private static CompletableFuture<ViewRenderable> GifLView000;
    private static CompletableFuture<ViewRenderable> GifLView001;
    private static CompletableFuture<ViewRenderable> TextLView000;
    private static CompletableFuture<ModelRenderable> spider;

    private static final Color CHROMA_KEY_COLOR = new Color(1.0f,1.0f,1.0f,0.0f);//0.1843f, 1.0f, 0.098f);
    private static final float VIDEO_HEIGHT_METERS = 0.05f;

    private ModelAnimator animator;
    private int nextAnimation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public AugmentedImageNode(Context context) {
////////////////////////////////////////////////////////////////////////////////////////////////////
        if (GifLView000 == null) {
////////////////Visualizacion de los GIFs///////////////////////////////////////////////////////////
//            GifLView000 =
//                    ViewRenderable.builder()
//                            .setView(context,R.layout.test_view000)
//                            .build();
//            GifLView001 =
//                    ViewRenderable.builder()
//                            .setView(context,R.layout.test_view001)
//                            .build();
////////////////////////////////////////////////////////////////////////////////////////////////////
            TextLView000 =
                    ViewRenderable.builder()
                            .setView(context,R.layout.test_view000)
                            .build();
//            spider =
//                    ModelRenderable.builder()
//                            .setSource(context, R.raw.spider_3)
//                            .build();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setImage(AugmentedImage image, Context context) {
        this.image = image;

        ExternalTexture texture = new ExternalTexture();
////////////Visualiza los GIFs//////////////////////////////////////////////////////////////////////
//        if (!GifLView000.isDone() || !GifLView001.isDone()) {
//            CompletableFuture.allOf(GifLView000,GifLView001)
//                    .thenAccept((Void aVoid) -> setImage(image,context))
//                    .exceptionally(
//                            throwable -> {
//                                Log.e(TAG, "Exception loading", throwable);
//                                return null;
//                            });
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////

/*        if (!ulCorner.isDone() || !spider.isDone()) {
            CompletableFuture.allOf(ulCorner, spider)
                    .thenAccept((Void aVoid) -> setImage(image,context))
                    .exceptionally(
                            throwable -> {
                                Log.e(TAG, "Exception loading", throwable);
                                return null;
                            });
        }*/


        // Set the anchor based on the center of the image.
        setAnchor(image.createAnchor(image.getCenterPose()));

        //        // Make the 4 corner nodes.
        Vector3 localPosition = new Vector3();
        Vector3 localScale = new Vector3();
        Node objectNode;
/////////////////////////////////Visualizacion del GIFs/////////////////////////////////////////////

//
//        /*Para visualizar Animacion Gifl*/
//        Vector3 localPosition = new Vector3();
//        Vector3 localScale = new Vector3();
//        Node objectNode;
//        //Tamano del gif en metros (250dp del layout -> 1metro)
//        localPosition.set( -image.getExtentX()*0.25f, 0.0f, 0.0f);//image.getExtentZ() * 0.5f
//        //Escala del gif en porcentaje
//        localScale.set(image.getExtentX()*0.5f, image.getExtentZ()*0.5f, 1.0f);
//        objectNode = new Node();
//        objectNode.setParent(this);
//        objectNode.setLocalRotation(Quaternion.axisAngle(Vector3.right(), -90.0f));
//        objectNode.setLocalPosition(localPosition);
//        objectNode.setLocalScale(localScale);
//
//        switch (image.getIndex()){
//            case 0:
//                objectNode.setRenderable(GifLView000.getNow(null));
//                Log.e(TAG, "Hemos " + String.valueOf(image.getIndex()));
//                break;
//            case 1:
//                objectNode.setRenderable(GifLView001.getNow(null));
//                Log.e(TAG, "Hemos " + String.valueOf(image.getIndex()));
//                break;
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////





//        // Upper left corner.
//        localPosition.set( 0.0f * image.getExtentX(), 0.0f, 0.25f * image.getExtentZ());
//        localScale.set(0.05f, 0.05f, 1.0f);
//        objectNode = new Node();
//        objectNode.setParent(this);
//        objectNode.setLocalRotation(Quaternion.axisAngle(Vector3.right(), -90.0f));
//        objectNode.setLocalPosition(localPosition);
//        objectNode.setLocalScale(localScale);
//        objectNode.setRenderable(ulCorner.getNow(null));


//        localPosition.set(0.0f * image.getExtentX(), 0.0f, 0.0f * image.getExtentZ());
//        localScale.set(0.05f, 0.05f, 0.05f);
//        objectNode = new Node();
//        objectNode.setParent(this);
//        objectNode.setLocalPosition(localPosition);
//        objectNode.setLocalScale(localScale);
//        //objectNode.setRenderable(spider.getNow(null));



        switch (image.getIndex()){
            case 0:
                ModelRenderable.builder()
                        .setSource(context, R.raw.chroma_key_video)
                        .build()
                        .thenAccept(
                                renderable -> {
                                    videoRenderable = renderable;
                                    renderable.getMaterial().setExternalTexture("videoTexture", texture);
                                    //renderable.getMaterial().setFloat4("keyColor", CHROMA_KEY_COLOR);
                                    renderable.getMaterial().setBoolean("disableChromaKey",true);
                                })
                        .exceptionally(
                                throwable -> {
                                    return null;
                                });


                mediaPlayer = MediaPlayer.create(context, R.raw.video);
                mediaPlayer.setSurface(texture.getSurface());
                mediaPlayer.setLooping(false);

                localPosition.set( -0.2f * image.getExtentX(), 0.0f, -0.22f * image.getExtentZ());
                objectNode = new Node();
                objectNode.setParent(this);
                objectNode.setLocalRotation(Quaternion.axisAngle(Vector3.right(), -90.0f));
                objectNode.setLocalPosition(localPosition);
                float videoWidth = mediaPlayer.getVideoWidth();
                float videoHeight = mediaPlayer.getVideoHeight();

                objectNode.setLocalScale(
                        new Vector3(
                                VIDEO_HEIGHT_METERS * (videoWidth / videoHeight), VIDEO_HEIGHT_METERS, 1.0f));
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Node finalobjectNode = objectNode;
                    texture
                            .getSurfaceTexture()
                            .setOnFrameAvailableListener(
                                    (SurfaceTexture surfaceTexture) -> {
                                        finalobjectNode.setRenderable(videoRenderable);
                                        texture.getSurfaceTexture().setOnFrameAvailableListener(null);
                                    });
                } else {
                    objectNode.setRenderable(videoRenderable);
                }
                break;
            case 1:
                if (!TextLView000.isDone()) {
                    CompletableFuture.allOf(TextLView000)
                            .thenAccept((Void aVoid) -> setImage(image,context))
                            .exceptionally(
                                    throwable -> {
                                        Log.e(TAG, "Exception loading", throwable);
                                        return null;
                                    });
                }
                //Tamano del gif en metros (250dp del layout -> 1metro)
                localPosition.set( -image.getExtentX()*0.25f, 0.0f, 0.0f);//image.getExtentZ() * 0.5f
                //Escala del gif en porcentaje
                localScale.set(image.getExtentX()*0.5f, image.getExtentZ()*0.5f, 1.0f);
                objectNode = new Node();
                objectNode.setParent(this);
                objectNode.setLocalRotation(Quaternion.axisAngle(Vector3.right(), -90.0f));
                objectNode.setLocalPosition(localPosition);
                objectNode.setLocalScale(localScale);
                objectNode.setRenderable(TextLView000.getNow(null));
                Log.e(TAG, "Hemos " + String.valueOf(image.getIndex()));
                break;
        }

    }

    public AugmentedImage getImage() {
        return image;
    }
//    public void onPlayAnimation(){
//        Log.e(TAG, "Aqui" + String.valueOf(spider.getNow(null).getAnimationDataCount()));
//        if (animator == null || !animator.isRunning()) {
//            AnimationData data = spider.getNow(null).getAnimationData(randint(1,spider.getNow(null).getAnimationDataCount()) - 1);
//            nextAnimation = (nextAnimation + 1) % spider.getNow(null).getAnimationDataCount();
//            Log.e(TAG, String.valueOf(spider.getNow(null).getAnimationDataCount()));
//            animator = new ModelAnimator(data, spider.getNow(null));
//            animator.start();
//            Log.d(
//                    TAG,
//                    String.format(
//                            "Starting animation %s - %d ms long", data.getName(), data.getDurationMs()));
//        }
//    }

//    public int randint(int min, int max)
//    {
//        // define the range
//        int range = max - min + 1;
//
//        // generate random numbers within 1 to 10
//        int rand = (int)(Math.random() * range) + min;
//        return rand;
//    }

}

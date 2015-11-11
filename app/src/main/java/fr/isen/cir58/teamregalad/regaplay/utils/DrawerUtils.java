package fr.isen.cir58.teamregalad.regaplay.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import fr.isen.cir58.teamregalad.regaplay.R;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.RegaplayListsActivity;
import fr.isen.cir58.teamregalad.regaplay.ui.activities.FilesListActivity;

/**
 * Created by maxime on 08/11/15.
 */
public class DrawerUtils {

    private Drawer response;
    private Activity activity;
    final PrimaryDrawerItem audioActivity = new PrimaryDrawerItem().withName("Audio explorer").withIcon(R.drawable.ic_music_black);
    final PrimaryDrawerItem fileActivity = new PrimaryDrawerItem().withName("File explorer").withIcon(R.drawable.ic_folder_black);
    final SecondaryDrawerItem about = new SecondaryDrawerItem().withName("RegaPlay by Team Regalade").withEnabled(false);

    public DrawerUtils(Activity activity) {
        this.activity = activity;
    }

    public void initializeDrawer(Activity activity){


        this.response = new DrawerBuilder().withActivity(activity)
                .addDrawerItems(
                        audioActivity,
                        fileActivity,
                        new DividerDrawerItem(),
                        about
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        updatedSelection();
                        return true;
                    }
                })
                .build();
    }

    public void updatedSelection(){
        Integer selected = response.getCurrentSelection();
        System.out.println("Regaplay update selection : " + selected);
        //Toast.makeText(activity, selected, Toast.LENGTH_LONG).show();
        if(selected == audioActivity.getIdentifier() ){
            if(this.activity.getClass() != RegaplayListsActivity.class){
                Intent fileIntent = new Intent(this.activity, RegaplayListsActivity.class);
                this.activity.startActivity(fileIntent);
                }

        }else if(selected == fileActivity.getIdentifier()){
            if(this.activity.getClass() != FilesListActivity.class){
                Intent fileIntent = new Intent(this.activity, FilesListActivity.class);
                this.activity.startActivity(fileIntent);
            }
        }
    }

    public void setSelected(int selected){
        if(selected == 1 ){
            response.setSelection(audioActivity.getIdentifier());
        }else if(selected == 2){
            response.setSelection(fileActivity.getIdentifier());
        }
    }
}

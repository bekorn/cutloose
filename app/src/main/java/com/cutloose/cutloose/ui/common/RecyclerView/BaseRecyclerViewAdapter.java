package com.cutloose.cutloose.ui.common.RecyclerView;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BaseRecyclerViewAdapter <Model> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mBinding;

        public ViewHolder( View instructorView, ViewDataBinding binding ) {
            super( instructorView );

            mBinding = binding;
        }

        private void bind( Model model ) {
            viewHolderBinder( mBinding, model );
//            mBinding.setVariable( BR.viewModel, new EventItemViewModel( (Event) model ) );
        }
    }

    protected abstract void viewHolderBinder( ViewDataBinding binding, Model model );

    protected abstract int getItemViewId();

    protected final MutableLiveData<ArrayList<Model>> mData = new MutableLiveData<>();

    public BaseRecyclerViewAdapter( BaseRecyclerViewModel<Model> baseRecyclerViewModel, LifecycleOwner lifecycleOwner ) {
        super();

        mData.setValue( new ArrayList<Model>() );

        baseRecyclerViewModel.getLiveData().observe( lifecycleOwner, new Observer<ArrayList<Model>>() {
            @Override
            public void onChanged( @Nullable ArrayList<Model> changedData ) {
                setData( changedData );
            }
        } );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from( parent.getContext() ),
                getItemViewId(),
                parent,
                false
        );

        return new ViewHolder( binding.getRoot(), binding );
    }

    @Override
    public void onBindViewHolder( @NonNull BaseRecyclerViewAdapter.ViewHolder holder, int position ) {
        if( mData.getValue() != null ) {
            holder.bind( mData.getValue().get( position ) );
        }
    }

    @Override
    public int getItemCount() {
        return mData.getValue() == null ? 0 : mData.getValue().size();
    }

    public void setData( ArrayList<Model> changedData ) {
        mData.setValue( changedData );
        notifyDataSetChanged();
    }
}
